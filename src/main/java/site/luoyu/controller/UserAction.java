package site.luoyu.controller;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import site.luoyu.dao.Books;
import site.luoyu.model.Book;
import site.luoyu.model.User;
import site.luoyu.service.BooksService;
import site.luoyu.util.QueryTool;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;

/**
 * 这个用来实现用户的行为，例如发布图书，浏览图书。查看图书详情等。
 * Created by xd on 2016/9/14.
 */
@Controller
@RequestMapping("/userAction")
public class UserAction {

    private static final Logger log = LogManager.getLogger(UserAction.class);

    @Autowired
    private BooksService booksService;

    /**
     * 返回发布图书页面
     * @param model
     * @return
     *      上传图书页面
     */
    @RequestMapping("/publishBookPage")
    public String getPublishBookPage(Model model){

        Book book = new Book();
        model.addAttribute("book",book);
        return "uploadBooks";
    }

    /**
     * 发布用户图书
     * @throws IOException 
     */
    //todo 这里细作的话需要支持多本书同时发布，我现在只是发完一本后跳转到发布页面，这样不太好
    @RequestMapping(value = "/publishBook",method = RequestMethod.POST)
    public String publishBookSale(MultipartHttpServletRequest multipartHttpServletRequest, HttpSession session) throws IOException {

        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        Map BookParameter = multipartHttpServletRequest.getParameterMap();

        //在数据库中存放所上传图片的路径信息
        List<String> path = null;
        
        log.info("用户 发布图书销售信息 ");
        User user = (User) session.getAttribute("user");
        if(user == null)return "redirect:/userManage/loginPage";
        else {
        	//上传图片封面，并将路径信息保存到数据库
            path = booksService.uploadCover(multipartHttpServletRequest, user, fileMap);
        }
        booksService.publishBook(BookParameter,path,user);
        return "redirect:/userAction/MainPage";
    }

    /**
     * 浏览主页面
     * @throws JSONException 
     */
    @RequestMapping("/MainPage")
    public String browseMainPage(HttpServletRequest request,Model model) throws JSONException{

        //todo 从前端获得排序策略，策略将来还有可能增加
        Pageable pageable = QueryTool.buildPageRequest(0, 12, "price");
        Page<Books> pages = booksService.getBooksByPage(pageable);
        //jstl只支持基本类型的遍历，不支持iterator
        List<Books> booksList = new ArrayList<>();
        Iterator<Books> iterator = pages.iterator();
        while (iterator.hasNext()){
            booksList.add(iterator.next());
        }
        model.addAttribute("pages",booksList);
        log.info("获得图书列表 针对用户的兴趣进行排序显示");
        return "MainPage";
    }

    /**
     * 搜索图书
     * 这里是一个难点，如何分词等问题都值得思考
     */
    @RequestMapping("/searchBooks")
    public void searchBooks(){}
}
