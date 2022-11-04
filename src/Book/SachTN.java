package Book;

public class SachTN extends Sach{
    private static int slTungLoai;
    private int slTungLoai1;
    private static String loai;
    private String loai1;

    static {
        loai = "Thiếu Nhi";
    }
    public SachTN() {

    }

    public SachTN(String maSach, String tenSach, String tenTacGia, int nxb, int soLuongMoiCuon) {
        super(maSach, tenSach, tenTacGia, nxb, soLuongMoiCuon);
    }

    public int getSlTungLoai() {
        return slTungLoai;
    }

    public void setSlTungLoai(int slTungLoai) {
        SachTN.slTungLoai = slTungLoai;
    }

    public int getSlTungLoai1() {
        return slTungLoai1;
    }

    public void setSlTungLoai1(int slTungLoai1) {
        this.slTungLoai1 = slTungLoai1;
    }
    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getLoai1() {
        return loai1;
    }

    public void setLoai1(String loai1) {
        this.loai1 = loai1;
    }
}
