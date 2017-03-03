package com.supinfo.jzipper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class ZipCompresser {
  private final String archive;
  private final String fileName;

  ZipCompresser(String archive, String fileName) {
    this.archive = archive;
    this.fileName = fileName;
  }

  void compress() {
    FileOutputStream fos = null;
    ZipOutputStream zos = null;
    FileInputStream fis = null;
    ZipEntry entry;
    try {
      fos = new FileOutputStream(archive);
      zos = new ZipOutputStream(fos);

      File file = new File(fileName);
      byte[] buffer = new byte[(int) file.length()];

      fis = new FileInputStream(file);
      entry = new ZipEntry(file.getName());
      zos.putNextEntry(entry);

      while (fis.read(buffer) != -1) {
        zos.write(buffer);
      }
      zos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      if (fis != null) {
        fis.close();
      }
      if (zos != null) {
        zos.close();
      }
      if (fos != null) {
        fos.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
