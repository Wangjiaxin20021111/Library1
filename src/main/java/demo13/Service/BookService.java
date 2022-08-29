package demo13.Service;

import demo13.controller.Result;

/**
 * @author 25043
 */
public interface BookService {
    /**
     * 查询所有
     * 查询的集合@return
     */
    Result selectBookAll();

    /**
     * 查询图书然后借出
     * 图书编号@param bookId
     * 图书数量@param count
     * 集合@return
     */
    Result selectTheseBookThenBorrow(String bookId, String count);

    /**
     * 归还图书
     * 书本id@param bookId
     * 归还数量@param count
     * 状态集合@return
     */
    Result selectTheseBookThenReturn(String bookId, String count);

    /**
     * 添加图书
     * isbn@param isbn
     * 名称@param name
     * 价格@param prize
     * 作者@param writer
     * 数量@param count
     * 添加信息集合@return
     */
    Result addBook(String isbn, String name, String prize, String writer, String count);
}
