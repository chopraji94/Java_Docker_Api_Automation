package Models;

public class DataObject {
    private String year;
    private String price;
    private String CPUmodel;
    private String hardDiskSize;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(String hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }

    public String getCPUmodel() {
        return CPUmodel;
    }

    public void setCPUmodel(String CPUmodel) {
        this.CPUmodel = CPUmodel;
    }
}
