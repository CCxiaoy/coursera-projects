/**
 * 在这里给出对类 ImageToGray 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import java.io.*;

public class ImageToGray {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel outP : outImage.pixels()) {
            Pixel inP = inImage.getPixel(outP.getX(), outP.getY());
            int average = (inP.getRed() + inP.getGreen() + inP.getBlue()) / 3;
            outP.setRed(average);
            outP.setGreen(average);
            outP.setBlue(average);
        }
        return outImage;
    }
    
    public void imageSaver(ImageResource inImage, ImageResource outImage, String prefix) {
        String fname = inImage.getFileName();
        String newName = prefix + fname;
        outImage.setFileName(newName);
        outImage.save();
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource grayImage = makeGray(inImage);
            imageSaver(inImage, grayImage, "gray-");
        }
    }
}
