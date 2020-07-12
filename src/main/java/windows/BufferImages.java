package windows;

import algo.Bipartite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class for buffering avatars.
 */
class BufferImages {
    ArrayList<Image> listPhotos = new ArrayList<>();

    /**
     * Constructor.
     * @param url Link on photo.
     * @return Image
     * @throws IOException -
     */
    public static Image getImageFromURL(String url) throws IOException {
        URL u = new URL(url);
        BufferedImage image = ImageIO.read(u);
        image = image.getSubimage(image.getWidth() / 2 - 25, image.getHeight() / 2 - 25, 50, 50);
        ImageIcon ret = new ImageIcon(image);
        return ret.getImage();
    }
}

/**
 * Class for buffering avatars of users.
 */
class BufferImagesUsers extends BufferImages {
    /**
     * Constructor.
     * @param bip Bipartite graph.
     * @throws IOException -
     */
    BufferImagesUsers(Bipartite bip) throws IOException {
        for (int i = 0; i < bip.getFirstSide().size(); ++i) {
            listPhotos.add(getImageFromURL(bip.getFirstSide().get(i).getItemData().photo));
        }
    }
}

/**
 * Class for buffering avatars of groups.
 */
class BufferImagesGroups extends BufferImages {
    /**
     * Constructor.
     * @param bip Bipartite graph.
     * @throws IOException -
     */
    BufferImagesGroups(Bipartite bip) throws IOException {
        for (int i = 0; i < bip.getSecondSide().size(); ++i) {
            listPhotos.add(getImageFromURL(bip.getSecondSide().get(i).getItemData().photo));
        }
    }
}

