package com.mobilgarson.homebeans.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.primefaces.model.UploadedFile;

public class ImageOperation
{

    public static String getImageStringBase64(UploadedFile file)
    {
        String response = "";
        InputStream inputstream = null;

        try
        {
            String contentType = file.getContentType();

            inputstream = file.getInputstream();

            BufferedImage bufferedImage = ImageIO.read(inputstream);

            if (bufferedImage != null)
            {
                java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
                ImageIO.write(bufferedImage, contentType.replace("image/", ""), os);
                byte[] data = os.toByteArray();
                response = Base64.getEncoder().encodeToString(data);
            }

        } catch (IOException ex)
        {
            Logger.getLogger(ImageOperation.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally
        {
            try
            {
                inputstream.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ImageOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }
}
