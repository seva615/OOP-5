package sample.plugins;

import java.io.File;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class FileSystemsZip extends Zip {
   @Override
   public void zip(String extension) {
      Map<String, String> env = new HashMap<>();
      env.put("create", "true");

      URI uri = URI.create("jar:file:/java1/oop3fx/filesystems.zip");

      try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
         Path pathInZipfile = zipfs.getPath(srcName(extension));

         Files.copy(new File(srcName(extension)).toPath(), pathInZipfile, StandardCopyOption.REPLACE_EXISTING);
      } catch (Exception ignored) {
         System.out.println(ignored);
      }
   }

   @Override
   public void unzip(String extension) {

   }
}
