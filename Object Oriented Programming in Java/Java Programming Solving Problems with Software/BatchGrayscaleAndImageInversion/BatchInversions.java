/**
 * 在这里给出对类 BatchInversions 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel outP : outImage.pixels()) {
            Pixel inP = inImage.getPixel(outP.getX(), outP.getY());
            int invertR = 255 - inP.getRed();
            int invertG = 255 - inP.getGreen();
            int invertB = 255 - inP.getBlue();
            outP.setRed(invertR);
            outP.setGreen(invertG);
            outP.setBlue(invertB);
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
            ImageResource invertedImage = makeInversion(inImage);
            imageSaver(inImage, invertedImage, "inverted-");
        }
    }
}
