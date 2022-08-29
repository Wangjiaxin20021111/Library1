package demo13.po;

/**
 * @author 25043
 */
public class Book {
    /**
     * 书本编号
     */
    private int bookId;
    /**
     * 书本isbn
     */
    private String isbn;
    /**
     * 书本名称
     */
    private String name;
    /**
     * 书本数量
     */
    private int count;
    /**
     * 书本价格
     */
    private float prize;
    /**
     * 作者
     */
    private String writer;
    /**
     * 书本状态
     */
    private int bookState;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getBookState() {
        return bookState;
    }

    public void setBookState(int bookState) {
        this.bookState = bookState;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", ISBN='" + isbn + '\'' + ", name='" + name + '\'' + ", count=" + count + ", prize=" + prize + ", writer='" + writer + '\'' + ", bookState=" + bookState + '}';
    }
}
