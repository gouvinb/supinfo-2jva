package com.supinfo.jzipper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class ZipDecompresser {

  private final String archive;

  ZipDecompresser(String archive) {
    this.archive = archive;
  }

  void decompress() {
    FileInputStream fis = null;
    ZipInputStream zis = null;
    FileOutputStream fos;
    ZipEntry entry;
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
}
