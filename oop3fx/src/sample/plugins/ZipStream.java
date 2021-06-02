package sample.plugins;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipStream extends Zip {
   @Override
   public void zip(String extension) {
      try (
              ZipOutputStream zos = new ZipOutputStream(
                      new FileOutputStream("zipstream.zip"));
              FileInputStream fis = new FileInputStream(srcName(extension))
      ) {

         ZipEntry zipEntry = new ZipEntry(srcName(extension));
         zos.putNextEntry(zipEntry);

         byte[] buffer = new byte[1024];
         int len;
         while ((len = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
         }
         zos.closeEntry();
      } catch (Exception ignored) {
         System.out.println(ignored);
      }

   }

   @Override
   public void unzip(String extension) {

   }
}
