package com.zzu.util;
import java.awt.Image;
/**
 * 图片信息类,对其操作在daoImp.Pictire里
 * @author zongzan
 *
 */
public class View {
 
    /**
     * 图片标题
     */
    private String title;
    /**
     * 图片内容
     */
    private String content;
    /**
     * 图片图片
     */
    private Image image;
     
    /**
     * 图片图片上传路径
     */
    private String imageFile;
      
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
 
    public Image getImage() {
        return image;
    }
 
    public void setImage(Image image) {
        this.image = image;    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getImageFile() {
        return imageFile;
    }
 
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}