using System;
using System.Runtime.InteropServices;
using dmstar.net.Proto;
using Google.Protobuf;

namespace dmstar.net.Utilities
{
    public class Util
    {
        private delegate bool ado_init();
        private unsafe delegate uint ado_process(byte* req, uint len, byte** res);
        private unsafe delegate void ado_release(byte* data);

        private static bool init_res = false;
        private static ado_process process_func;
        private static ado_release release_func;

        public static bool init()
        {
            IntPtr adoPtr;
            if (RuntimeInformation.IsOSPlatform(OSPlatform.Windows))
            {
#if DEBUG
                adoPtr = NativeLibrary.Load("D:\\workspace\\svn\\dmstar\\odbc\\odbc_rust\\target\\debug\\odbc_rust.dll");
#else
                var deepitKey = Microsoft.Win32.Registry.CurrentUser.OpenSubKey(@"SOFTWARE\Deepdt\Dmstar ODBC(x64) Driver");
                var install = deepitKey.GetValue("InstallLocation") + @"\o2jb.dll";
                adoPtr = NativeLibrary.Load(install);
#endif
            }
            else
            {
                adoPtr = NativeLibrary.Load("/usr/local/o2jb/libo2jb_64.so");
            }

            IntPtr init_method = NativeLibrary.GetExport(adoPtr, "ado_init");
            IntPtr process_method = NativeLibrary.GetExport(adoPtr, "ado_process");
            IntPtr release_method = NativeLibrary.GetExport(adoPtr, "ado_release");

            // convert "pointer" to delegate

            if (init_method != IntPtr.Zero)
            {
                ado_init init_func = (ado_init)Marshal.GetDelegateForFunctionPointer(init_method, typeof(ado_init));
                init_res = init_func();
            }

            if (process_method != IntPtr.Zero)
            {
                process_func = (ado_process)Marshal.GetDelegateForFunctionPointer(process_method, typeof(ado_process));
            }

            if (release_method != IntPtr.Zero)
            {
                release_func = (ado_release)Marshal.GetDelegateForFunctionPointer(release_method, typeof(ado_release));
            }

            return init_res;
        }

        public static T request<T>(MsgCode code, IMessage reqMsg)
        {
            ReqWrapper req = new ReqWrapper();
            req.Code = code;
            req.Data = reqMsg.ToByteString();

            ResWrapper res = doRequest(req);
            if (!res.Success)
            {
                if (res.Msg != null)
                {
                    throw new SQLException(res.Msg);
                }
                throw new SQLException("request fail.");
            }

            IMessage resp = null;
            switch (code)
            {
                case MsgCode.LoadDriver:
                    resp = new LoadDriverResponse();
                    break;
                case MsgCode.OpenConnection:
                    resp = new OpenConnectionResponse();
                    break;
                case MsgCode.GetTransactionIsolation:
                    resp = new GetTransactionIsolationResponse();
                    break;
                case MsgCode.CreateStatement:
                    resp = new CreateStatementResponse();
                    break;
                case MsgCode.ExecuteStatement:
                    resp = new ExecuteStatementResponse();
                    break;
                case MsgCode.ReadResultSet:
                    resp = new ReadResultSetResponse();
                    break;
                case MsgCode.CloseConnection:
                case MsgCode.ChangeCatalog:
                case MsgCode.SetAutoCommit:
                case MsgCode.SetTransactionIsolation:
                case MsgCode.Rollback:
                case MsgCode.Commit:
                case MsgCode.CancelStatement:
                case MsgCode.CloseStatement:
                case MsgCode.SetParameter:

                case MsgCode.CloseResultSet:
                    resp = new Empty();
                    break;
            }

            if (resp != null)
            {
                resp.MergeFrom(res.Data);
                return (T)resp;
            }

            throw new SQLException("No implement.");
        }

        private static ResWrapper doRequest(ReqWrapper req)
        {
            if (!init_res)
            {
                throw new SQLException("init failed.");
            }

            byte[] reqBytes = req.ToByteArray();
            int reqLen = reqBytes.Length;

            IntPtr reqPtr = Marshal.AllocHGlobal(reqLen);
            Marshal.Copy(reqBytes, 0, reqPtr, reqLen);

            int size = Marshal.SizeOf(typeof(IntPtr));
            IntPtr resPtr = Marshal.AllocHGlobal(size);

            unsafe
            {
                byte* resDataPtr = (byte*)resPtr.ToPointer();

                uint resLen = process_func((byte*)reqPtr.ToPointer(), (uint)reqLen, &resDataPtr);
                byte[] resBytes = new byte[resLen];
#if DEBUG
                Console.WriteLine("request len:{0}, response len:{1}", reqLen, resLen);
#endif
                if (resLen > 0)
                {
                    for (uint i = 0; i < resLen; i++)
                    {
                        resBytes[i] = *(resDataPtr + i);// Marshal.ReadByte(dataPtr, (int)i);
                    }

                    release_func(resDataPtr);
                }

                Marshal.FreeHGlobal(reqPtr);
                Marshal.FreeHGlobal(resPtr);

                if (resLen == 0)
                {
                    throw new SQLException("response data is empty.");
                }

                ResWrapper w = new ResWrapper();
                w.MergeFrom(resBytes);

                return w;
            }
        }
    }
}






//using System;
//using System.Runtime.InteropServices;
//using dmstar.net.Proto;
//using Google.Protobuf;

//namespace dmstar.net.Utilities
//{
//    public class Util
//    {
//        private delegate bool ado_init();
//        private unsafe delegate uint ado_process(byte* req, uint len, byte** res);
//        private unsafe delegate void ado_release(byte* data);

//        private static bool init_res = false;
//        private static ado_process process_func;
//        private static ado_release release_func;

//        static Util()
//        {
//            IntPtr adoPtr;
//            if (RuntimeInformation.IsOSPlatform(OSPlatform.Windows))
//            {
//#if DEBUG
//                adoPtr = NativeLibrary.Load("D:\\workspace\\svn\\dmstar\\odbc\\odbc_rust\\target\\debug\\odbc_rust.dll");
//#else
//                var deepitKey = Microsoft.Win32.Registry.CurrentUser.OpenSubKey(@"SOFTWARE\Deepdt\Dmstar ODBC(x64) Driver");
//                var install = deepitKey.GetValue("InstallLocation") + @"\o2jb.dll";
//                adoPtr = NativeLibrary.Load(install);
//#endif
//            }
//            else
//            {
//                adoPtr = NativeLibrary.Load("/usr/local/o2jb/libo2jb_64.so");
//            }

//            IntPtr init_method = NativeLibrary.GetExport(adoPtr, "ado_init");
//            IntPtr process_method = NativeLibrary.GetExport(adoPtr, "ado_process");
//            IntPtr release_method = NativeLibrary.GetExport(adoPtr, "ado_release");

//            // convert "pointer" to delegate

//            if (init_method != IntPtr.Zero)
//            {
//                ado_init init_func = (ado_init)Marshal.GetDelegateForFunctionPointer(init_method, typeof(ado_init));
//                init_res = init_func();
//            }

//            if (process_method != IntPtr.Zero)
//            {
//                process_func = (ado_process)Marshal.GetDelegateForFunctionPointer(process_method, typeof(ado_process));
//            }

//            if (release_method != IntPtr.Zero)
//            {
//                release_func = (ado_release)Marshal.GetDelegateForFunctionPointer(release_method, typeof(ado_release));
//            }
//        }

//        public static T request<T>(MsgCode code, IMessage reqMsg)
//        {
//            ReqWrapper req = new ReqWrapper();
//            req.Code = code;
//            req.Data = reqMsg.ToByteString();

//            ResWrapper res = doRequest(req);
//            if (!res.Success)
//            {
//                if (res.Msg != null)
//                {
//                    throw new SQLException(res.Msg);
//                }
//                throw new SQLException("request fail.");
//            }

//            IMessage resp = null;
//            switch (code)
//            {
//                case MsgCode.LoadDriver:
//                    resp = new LoadDriverResponse();
//                    break;
//                case MsgCode.OpenConnection:
//                    resp = new OpenConnectionResponse();
//                    break;
//                case MsgCode.GetTransactionIsolation:
//                    resp = new GetTransactionIsolationResponse();
//                    break;
//                case MsgCode.CreateStatement:
//                    resp = new CreateStatementResponse();
//                    break;
//                case MsgCode.ExecuteStatement:
//                    resp = new ExecuteStatementResponse();
//                    break;
//                case MsgCode.ReadResultSet:
//                    resp = new ReadResultSetResponse();
//                    break;
//                case MsgCode.CloseConnection:
//                case MsgCode.ChangeCatalog:
//                case MsgCode.SetAutoCommit:
//                case MsgCode.SetTransactionIsolation:
//                case MsgCode.Rollback:
//                case MsgCode.Commit:
//                case MsgCode.CancelStatement:
//                case MsgCode.CloseStatement:
//                case MsgCode.SetParameter:

//                case MsgCode.CloseResultSet:
//                    resp = new Empty();
//                    break;
//            }

//            if (resp != null)
//            {
//                resp.MergeFrom(res.Data);
//                return (T)resp;
//            }

//            throw new SQLException("No implement.");
//        }

//        private static ResWrapper doRequest(ReqWrapper req)
//        {
//            if (!init_res)
//            {
//                throw new SQLException("init failed.");
//            }

//            byte[] reqBytes = req.ToByteArray();
//            int reqLen = reqBytes.Length;

//            IntPtr reqPtr = Marshal.AllocHGlobal(reqLen);
//            Marshal.Copy(reqBytes, 0, reqPtr, reqLen);

//            int size = Marshal.SizeOf(typeof(IntPtr));
//            IntPtr resPtr = Marshal.AllocHGlobal(size);

//            unsafe
//            {
//                byte* resDataPtr = (byte*)resPtr.ToPointer();

//                uint resLen = process_func((byte*)reqPtr.ToPointer(), (uint)reqLen, &resDataPtr);
//                byte[] resBytes = new byte[resLen];

//                Console.WriteLine("request len:{0}, response len:{1}", reqLen, resLen);

//                if (resLen > 0)
//                {

//                    //IntPtr dataPtr = resPtr;// Marshal.ReadIntPtr(resPtr);
//                    //byte** dataPtr = (byte**)resPtr.ToPointer();
//                    //Console.WriteLine(dataPtr);

//                    //byte* data = *dataPtr;
//                    for (uint i = 0; i < resLen; i++)
//                    {
//                        resBytes[i] = *(resDataPtr + i);// Marshal.ReadByte(dataPtr, (int)i);
//                    }

//                    //byte** src = (byte**)resPtr.ToPointer();
//                    //byte* data = *src;

//                    //for (uint i = 0; i < resLen; i++)
//                    //{
//                    //    resBytes[i] = *(data + i);
//                    //}

//                    //release_func(resDataPtr);
//                }

//                Marshal.FreeHGlobal(reqPtr);
//                Marshal.FreeHGlobal(resPtr);

//                if (resLen == 0)
//                {
//                    throw new SQLException("response data is empty.");
//                }


//                ResWrapper w = new ResWrapper();
//                w.MergeFrom(resBytes);

//                return w;
//            }
//        }
//    }
//}
