package Books;

import General.Common.ISaveLoad;
import General.Input.NInteger;
import General.Input.NString;
import General.Menu.IMenu;
import General.Menu.RunnableMenu;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class BookManagement implements ISaveLoad, IMenu {
    public ArrayList<Shelf> shelves = new ArrayList<>();
    private static int latestShelfId = 0;
    private boolean seeBorrowed = true;

    public static void setLatestShelfId(int latestShelfId) {
        BookManagement.latestShelfId = latestShelfId;
    }

    private static BookManagement instance;

    public static BookManagement getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BookManagement();
        }

        return instance;
    }

    private BookManagement() {
        load();
    }


//region BOOK



    public void addBookByInput() {
        RunnableMenu menu = new RunnableMenu("Thêm sách");
        for (var shelf : shelves) {
            menu.add("Thêm vào kệ " + shelf.getShelfName() +", ID: " + shelf.getShelfID(), shelf::addBookFromInput);
        }
        menu.show();
    }

//    region Generic Menu Impl
    private void byIDMenu(BiConsumer<ArrayList<AbstractMap.SimpleImmutableEntry<Shelf, Book>>, String> biConsumer) {
        String id = new NString("ID").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book -> book.getID().toUpperCase(Locale.ROOT).contains(id.toUpperCase(Locale.ROOT)));
        biConsumer.accept(shelfBookPairList, "Theo thể loại " + id);
    }

    private void byBookType(EBookType bookType, BiConsumer<ArrayList<AbstractMap.SimpleImmutableEntry<Shelf, Book>>, String> biConsumer) {
        var shelfBookPairList = findBook(book -> book.getMainType().equals(bookType) || book.getSubType().equals(bookType));
        biConsumer.accept(shelfBookPairList, "Theo thể loại " + bookType);
    }

    private void byBookTypeMenu(Consumer<EBookType> consumer) {
        RunnableMenu menu = new RunnableMenu("Theo thể loại");
        menu.add("Tâm Lý", () -> consumer.accept(EBookType.TAM_LY));
        menu.add("Thiếu Nhi", () -> consumer.accept(EBookType.THIEU_NHI));
        menu.add("Tiểu Thuyết", () -> consumer.accept(EBookType.TIEU_THUYET));
        menu.add("Văn Học", () -> consumer.accept(EBookType.VAN_HOC));
        menu.show();
    }

    private void byNameMenu(BiConsumer<ArrayList<AbstractMap.SimpleImmutableEntry<Shelf, Book>>, String> biConsumer) {
        String name = new NString("tên sách").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book -> book.getBookName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
        biConsumer.accept(shelfBookPairList, "Theo Tên " + name);
    }

//    endregion

//    region Find Book

    private ArrayList<AbstractMap.SimpleImmutableEntry<Shelf, Book>> findBook(Function<Book, Boolean> compareFunc) {
        ArrayList<AbstractMap.SimpleImmutableEntry<Shelf, Book>> shelfBookPairList = new ArrayList<>();

        for (var shelf: shelves) {
            for (var book: shelf.getBooks()) {
                if (compareFunc.apply(book)) {
                    shelfBookPairList.add(new AbstractMap.SimpleImmutableEntry<>(shelf, book));
                }
            }
        }
        return shelfBookPairList;
    }


    private void findBookAll() {
        RunnableMenu menu = new RunnableMenu("Toàn bộ");

        for (var shelf : shelves) {
            for (var book : shelf.getBooks()) {
                menu.add(book.getBookName(), ()->{
                    System.out.printf("Sách %s, tại kệ %s\n", book.getBookName(), shelf.getShelfName());
                });
            }
        }

        menu.show();
    }
    private void findBookMenu(ArrayList<AbstractMap.SimpleImmutableEntry<Shelf, Book>> shelfBookPairList, String name) {
        RunnableMenu menu = new RunnableMenu(name);

        for (var shelfBookPair : shelfBookPairList) {
            menu.add(String.valueOf(shelfBookPair.getValue().getBookName()), ()->{
                System.out.printf("Sách %s, tại kệ %s\n", shelfBookPair.getValue().getBookName(), shelfBookPair.getKey().getShelfName());
            });
        }

        menu.show();
    }

    private void findByBookType(EBookType bookType) {
        byBookType(bookType, this::findBookMenu);
    }

    private void findBookFromBookTypeMenu() {
        byBookTypeMenu(this::findByBookType);
    }

    private void findBookFromBookIDMenu() {
        byIDMenu(this::findBookMenu);
    }

    private void findBookFromBookNameMenu() {
        byNameMenu(this::findBookMenu);


    }

    private void findBookFromAuthorNameMenu() {
        String name = new NString("tên tác giả").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book -> book.getAuthorName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
        findBookMenu(shelfBookPairList, "Theo Tác Giả: " + name);
    }

    private void findBookFromAgeMenu() {
        Integer ratedAge = new NInteger("tuổi gợi ý").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book -> book.getRatedAge() <= ratedAge);
        findBookMenu(shelfBookPairList, "Theo Tuổi Gợi Ý: " + ratedAge);
    }

    private int intAnyAge(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void findBookAnyMenu() {
        String value = new NString("giá trị").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book ->
                    book.getID().toUpperCase(Locale.ROOT).contains(value.toUpperCase(Locale.ROOT)) ||
                    book.getBookName().toUpperCase(Locale.ROOT).contains(value.toUpperCase(Locale.ROOT)) ||
                    book.getAuthorName().toUpperCase(Locale.ROOT).contains(value.toUpperCase(Locale.ROOT)) ||
                    book.getRatedAge() <= intAnyAge(value)
                    );
        findBookMenu(shelfBookPairList, "Theo bất kỳ với: " + value);
    }



    public void findBookFromInput() {
        RunnableMenu menu = new RunnableMenu("Tìm sách");
        menu.add("Toàn bộ", this::findBookAll);
        menu.add("Theo ID", this::findBookFromBookIDMenu);
        menu.add("Theo thể loại", this::findBookFromBookTypeMenu);
        menu.add("Theo tên sách", this::findBookFromBookNameMenu);
        menu.add("Theo tên tác giả", this::findBookFromAuthorNameMenu);
        menu.add("Theo độ tuổi", this::findBookFromAgeMenu);
        menu.add("Theo bất kỳ", this::findBookAnyMenu);
        menu.show();
    }
//    endregion

//    region MOVE BOOK
    private void moveToShelf(Shelf fromShelf, Shelf toShelf, Book book) {
        fromShelf.getBooks().remove(book);
        toShelf.getBooks().add(book);
    }

    private void moveToShelfMenu(Shelf fromShelf, Book book) {
        RunnableMenu menu = new RunnableMenu("Chuyen sach " + book.getBookName() + " tu ke " + fromShelf.getShelfName() + " sang ");
        menu.setRunOnce(true);
        for (var shelf : shelves) {
            menu.add("Ke " + shelf.getShelfName() + ", ID: " + shelf.getShelfID(), ()->moveToShelf(fromShelf, shelf, book));
        }
        menu.show();
    }

    private void moveBookInShelf(Shelf shelf) {
        RunnableMenu menu = new RunnableMenu("Chuyen tu ke " + shelf.getShelfName());
        menu.setRunOnce(true);
        for (var book : shelf.getBooks()) {
            menu.add(book.getBookName(), ()->moveToShelfMenu(shelf, book));
        }
        menu.show();
    }

    public void moveBookByMenu() {
        RunnableMenu menu = new RunnableMenu("Chuyen sach");
        for (var shelf : shelves) {
            menu.add("Ke " + shelf.getShelfName() + ", ID: " + shelf.getShelfID(), ()->moveBookInShelf(shelf));
        }
        menu.show();
    }
//    endregion MOVE BOOK

//    region DELETE BOOK
//    region BY BOOK
//    region BY BOOK IMPL
    private void deleteBookAll() {
        RunnableMenu menu = new RunnableMenu("Toàn bộ");
        menu.setRunOnce(true);

        for (var shelf : shelves) {
            for (var book : shelf.getBooks()) {
                menu.add(book.getBookName(), ()->{
                    shelf.removeBook(book);
                });
            }
        }

        menu.show();
    }
    private void deleteBookMenu(ArrayList<AbstractMap.SimpleImmutableEntry<Shelf, Book>> shelfBookPairList, String name) {
        RunnableMenu menu = new RunnableMenu(name);
        menu.setRunOnce(true);

        for (var shelfBookPair : shelfBookPairList) {
            menu.add(String.valueOf(shelfBookPair.getValue().getBookName()), ()->{
                shelfBookPair.getKey().removeBook(shelfBookPair.getValue());
                menu.removeItem(String.valueOf(shelfBookPair.getValue().getBookName()));
            });
        }

        menu.show();
    }

    private void deleteByBookType(EBookType bookType) {
        var shelfBookPairList = findBook(book -> book.getMainType().equals(bookType) || book.getSubType().equals(bookType));

        deleteBookMenu(shelfBookPairList, "Theo thể loại " + bookType);
    }

    private void deleteBookFromBookTypeMenu() {
        RunnableMenu menu = new RunnableMenu("Theo thể loại");
        menu.add("Tâm Lý", () -> deleteByBookType(EBookType.TAM_LY));
        menu.add("Thiếu Nhi", () -> deleteByBookType(EBookType.THIEU_NHI));
        menu.add("Tiểu Thuyết", () -> deleteByBookType(EBookType.TIEU_THUYET));
        menu.add("Văn Học", () -> deleteByBookType(EBookType.VAN_HOC));
        menu.show();
    }

    private void deleteBookFromBookIDMenu() {
        String id = new NString("ID").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book -> book.getID().toUpperCase(Locale.ROOT).contains(id.toUpperCase(Locale.ROOT)));
        deleteBookMenu(shelfBookPairList, "Theo Tên " + id);
    }

    private void deleteBookFromBookNameMenu() {
        String name = new NString("tên sách").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book -> book.getBookName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
        deleteBookMenu(shelfBookPairList, "Theo Tên " + name);
    }

    private void deleteBookFromAuthorNameMenu() {
        String name = new NString("tên tác giả").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book -> book.getAuthorName().toUpperCase(Locale.ROOT).contains(name.toUpperCase(Locale.ROOT)));
        deleteBookMenu(shelfBookPairList, "Theo Tác Giả: " + name);
    }

    private void deleteBookFromAgeMenu() {
        Integer ratedAge = new NInteger("tuổi gợi ý").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book -> book.getRatedAge() <= ratedAge);
        deleteBookMenu(shelfBookPairList, "Theo Tuổi Gợi Ý: " + ratedAge);
    }

    private void deleteBookAnyMenu() {
        String value = new NString("giá trị").getFromInput().getValue();
        var shelfBookPairList =
                findBook(book ->
                        book.getID().toUpperCase(Locale.ROOT).contains(value.toUpperCase(Locale.ROOT)) ||
                                book.getBookName().toUpperCase(Locale.ROOT).contains(value.toUpperCase(Locale.ROOT)) ||
                                book.getAuthorName().toUpperCase(Locale.ROOT).contains(value.toUpperCase(Locale.ROOT)) ||
                                book.getRatedAge() <= intAnyAge(value)
                );
        deleteBookMenu(shelfBookPairList, "Theo bất kỳ với: " + value);
    }
//    endregion

//    region DELETE BY BOOK MENU
    public void deleteBookFromInput() {
        RunnableMenu menu = new RunnableMenu("Xoa sách");
        menu.add("Toàn bộ", this::deleteBookAll);
        menu.add("Theo ID", this::deleteBookFromBookIDMenu);
        menu.add("Theo thể loại", this::deleteBookFromBookTypeMenu);
        menu.add("Theo tên sách", this::deleteBookFromBookNameMenu);
        menu.add("Theo tên tác giả", this::deleteBookFromAuthorNameMenu);
        menu.add("Theo độ tuổi", this::deleteBookFromAgeMenu);
        menu.add("Theo bất kỳ", this::deleteBookAnyMenu);
        menu.show();
    }
    //    endregion

//    endregion




//    region BY SHELF IMPL
    private void deleteBookInShelf(Shelf shelf) {
        RunnableMenu menu = new RunnableMenu("Xoa sach trong ke " + shelf.getShelfName());
        menu.setRunOnce(true);
        for (var book : shelf.getBooks()) {
            menu.add(book.getBookName(), ()->shelf.removeBook(book));
        }
        menu.show();
    }

    public void deleteBookFromShelfInMenu() {
        RunnableMenu menu = new RunnableMenu("Xoa sach");
        for (var shelf : shelves) {
            menu.add("Ke " + shelf.getShelfName() + ", ID: " + shelf.getShelfID(), ()->moveBookInShelf(shelf));
        }
        menu.show();
    }

//    endregion

    public void deleteBookFromMenu(){
        RunnableMenu menu = new RunnableMenu("Xoa sach");
        menu.add("Theo sách", this::deleteBookFromInput);
        menu.add("Theo kệ", this::deleteBookFromShelfInMenu);
        menu.show();
    }
//    endregion


//    region SHELF
    public void setShelves(ArrayList<Shelf> shelves) {
        this.shelves = shelves;
        setNewLatestShelfId();
    }


    private void addShelfByInput() {
        Shelf shelf = new Shelf();
        shelf.setFromInput();
        shelves.add(shelf);
    }

    private void deleteShelf(Shelf shelf) {
        if (shelf.getBooks().size() != 0) {
            System.out.println("Ke con sach, khong the xoa!");
            return;
        }

        shelves.remove(shelf);
    }

    private void deleteShelfByMenu() {
        RunnableMenu menu = new RunnableMenu("Xoa Ke");
        menu.setRunOnce(true);
        for (var shelf : shelves) {
            menu.add("Xoa ke " + shelf.getShelfName() + ", ID: " + shelf.getShelfID(), ()->deleteShelf(shelf));
        }

        menu.show();
    }
//    endregion

//    region GET BOOK
    public Book getBookMenu() {
        Book[] bookToGet = new Book[1];
        RunnableMenu menu = new RunnableMenu("Sach can lay");
        menu.setRunOnce(true);
        for (var shelf : shelves) {
            for (var book : shelf.getBooks()) {
                menu.add(book.getBookName(), ()->{
                    bookToGet[0] = book;
                });
            }
        }
        menu.show();
        return bookToGet[0];
    }

//    endregion

//    region SAVE LOAD
    public void save() {
        File file = new File("./src/Data/Book.bin");
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file, false);
            out = new ObjectOutputStream(fos);
            out.writeObject(shelves);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static int getNewID() {
        latestShelfId++;
        return latestShelfId;
    }

    private void setNewLatestShelfId() {
        int max = 0;
        for (var shelf : shelves) {
            max = Math.max(max, shelf.getShelfID());
        }
        setLatestShelfId(max);
    }

    public void load() {
        File file = new File("./src/Data/Book.bin");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fis);
            shelves = (ArrayList<Shelf>) oos.readObject();
            fis.close();
        } catch (ClassNotFoundException | IOException ignored) {
            System.out.println("Khoi tao chuong trinh Sach!");
        } finally {
            setNewLatestShelfId();
        }
    }

//    endregion





//    TODO Impl change book.
    @Override
    public void menu() {
        RunnableMenu menu = new RunnableMenu("Quản lý sách");
        menu.addBackgroundTask(this::save);
        menu.addOnReturnTask(()-> seeBorrowed = false);

        menu.addSection("Chung");
        menu.add("Tìm sách", this::findBookFromInput);
        menu.add("Chuyển sách", this::moveBookByMenu);
        menu.add("Chỉnh sách", this::moveBookByMenu);

        menu.addSection("Thêm");
        menu.add("Thêm sách", this::addBookByInput);
        menu.add("Thêm Kệ", this::addShelfByInput);

        menu.addSection("Xóa");
        menu.add("Xóa sách", this::deleteBookFromMenu);
        menu.add("Xóa kệ", this::deleteShelfByMenu);

        menu.addSection("Cai dat");
        menu.add("Bật/Tắt xem sách đã mượn", ()->{
            seeBorrowed = !seeBorrowed;
            System.out.println("Xem sach muon: " + seeBorrowed);
        });

        menu.addSection("Debug");

        menu.add("Debug", ()->{
            System.out.println(this.shelves);
        });

        menu.show();
    }

}
