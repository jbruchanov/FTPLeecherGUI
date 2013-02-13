//package com.scurab.java.ftpleechergui.gui;
//
//import org.apache.commons.net.ftp.FTPFile;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import java.awt.*;
//import java.io.IOException;
//
///**
// * Created with IntelliJ IDEA.
// * User: Joe Scurab
// * Date: 13.2.13
// * Time: 0:07
// * To change this template use File | Settings | File Templates.
// */
//public class FTPIconCellTableRenderer extends DefaultTableCellRenderer {
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        if(value != null && value instanceof FTPFile){
//            FTPFile file = (FTPFile)value;
//            try {
//                return new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/assets/file.png")));
//            } catch (IOException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//        }else{
//            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        }
//    }
//}
