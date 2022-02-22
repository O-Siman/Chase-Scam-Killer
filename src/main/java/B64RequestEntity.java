// import java.io.InputStream;
// import java.io.OutputStream;
// import org.apache.http.client.entity.RequestEntity;

// public class B64RequestEntity implements RequestEntity {

//     int size;
    
//     public B64RequestEntity(int size) {
//         super();
//         this.size = size;
//     }

//     public boolean isRepeatable() {
//         return true;
//     }

//     public String getContentType() {
//         return "text/plain; charset=UTF-8";
//     }
    
//     public void writeRequest(OutputStream out) {
//         InputStream in = new B64InputStream();
//         try {
//             int l;
//             byte[] buffer = new byte[1024];
//             while ((l = in.read(buffer)) != -1) {
//                 out.write(buffer, 0, l);
//             }
//         } finally {
//             in.close();
//         }
//     }

//     public long getContentLength() {
//         return file.length();
//     }
// }