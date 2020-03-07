package fun.imcoder.cloud.model;

import java.util.Comparator;
import java.util.List;

public class ImcoderFile implements Comparator<ImcoderFile> {
    private String name;
    private String path;
    private String content;
    private Boolean isFile;
    private List<ImcoderFile> node;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsFile() {
        return isFile;
    }

    public void setIsFile(Boolean isFile) {
        this.isFile = isFile;
    }

    public List<ImcoderFile> getNode() {
        return node;
    }

    public void setNode(List<ImcoderFile> node) {
        this.node = node;
    }

    @Override
    public int compare(ImcoderFile leftFile, ImcoderFile rightFile) {
        if (leftFile.isFile && !rightFile.isFile) {
            return 1;
        }

        if (!leftFile.isFile && rightFile.isFile) {
            return -1;
        }

        return leftFile.getName().compareTo(rightFile.getName());
    }

}
