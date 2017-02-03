package com.supinfo.jzipper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipDecompresser {

  private String archive;

  ZipDecompresser(String archive) {
    this.archive = archive;
  }

  void decompress() {
    FileInputStream fis = null;
    ZipInputStream zis = null;
    FileOutputStream fos = null;
    ZipEntry entry = null;
    try {
      fis = new FileInputStream(archive);
      zis = new ZipInputStream(fis);

      while ((entry = zis.getNextEntry()) != null) {
        int b;
        fos = new FileOutputStream("./" + entry.getName());

        while ((b = zis.read()) != -1) {
          fos.write(b);
        }
        fos.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      if (zis != null) {
        zis.close();
      }
      if (fis != null) {
        fis.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public String getArchive() {
    return archive;
  }

  public void setArchive(String archive) {
    this.archive = archive;
  }
}
