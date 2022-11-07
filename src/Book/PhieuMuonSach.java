package Book;

public class PhieuMuonSach {
    private Customer kh;
    private Staff nv;
    private Sach sach;
    private String ngayMuon;
    private String ngayTra;

    public PhieuMuonSach() {
    }

    public PhieuMuonSach(Customer kh, Staff nv, Sach sach, String ngayMuon, String ngayTra) {
        this.kh = kh;
        this.nv = nv;
        this.sach = sach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public Customer getKh() {
        return kh;
    }

    public void setKh(Customer kh) {
        this.kh = kh;
    }

    public Staff getNv() {
        return nv;
    }

    public void setNv(Staff nv) {
        this.nv = nv;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }
}
