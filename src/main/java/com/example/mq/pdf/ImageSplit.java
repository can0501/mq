package com.example.mq.pdf;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageSplit {
   static BufferedImage split(BufferedImage io, int x, int y) throws IOException {

       return Thumbnails.of(io).sourceRegion(Positions.TOP_LEFT, x, y)
                .size(x, y).asBufferedImage();
    }
}
