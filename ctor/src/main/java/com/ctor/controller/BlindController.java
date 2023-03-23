package com.ctor.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ctor.dto.BlindCommentsDTO;
import com.ctor.dto.BlindDTO;
import com.ctor.dto.BlindPageRequestDTO;
import com.ctor.service.BlindCommentsService;
import com.ctor.service.BlindService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BlindController {

	@Autowired
	BlindService blindService;
	@Autowired
	BlindCommentsService blindCommentsService;
	
	@GetMapping("blind")
	public void blind(BlindPageRequestDTO pageRequestDTO,Model model) {
		model.addAttribute("pageResObj",blindService.getList(pageRequestDTO));
	}
	
	@GetMapping("blindWrite")
	public void blindWrite() {
	}
	@GetMapping({"blindRead","blindModify"})
	public void read(long bno, @ModelAttribute("requestDTO") BlindPageRequestDTO pageRequestDTO, Model model) {
		BlindDTO dto = blindService.findById(bno);
		List<BlindCommentsDTO> commentsDTOs = blindCommentsService.getList(bno);
		model.addAttribute("dto",dto);
		model.addAttribute("comments",commentsDTOs);
		model.addAttribute("requestDTO",pageRequestDTO);
		model.addAttribute("pageResObj",blindService.getList(pageRequestDTO));
	}
	@GetMapping("blindDelete")
	public String blindDelete(long bno) {
		blindService.removeWithComments(bno);
		return "redirect:blind";
	}
	
	@PostMapping("blindWrite")
	public String postImage(BlindDTO dto,RedirectAttributes redirectAttributes) {
		System.out.println("=========입력받은값 :"+dto);
		Long bno = blindService.register(dto);
		redirectAttributes.addAttribute("bno",bno);
		return "redirect:blindRead";
	}
	@PostMapping("blindCommentWrite")
	public String commentWrite(BlindCommentsDTO dto,RedirectAttributes redirectAttributes) {
		blindCommentsService.register(dto);
		Long bno = dto.getBno();
		redirectAttributes.addAttribute("bno",bno);
		return "redirect:blindRead";
	}
	
	@PostMapping("fileUpload")
    public void postImage(MultipartFile upload, HttpServletResponse res, HttpServletRequest req){

        OutputStream out = null;
        PrintWriter printWriter = null;

        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");

        try{

            UUID uuid = UUID.randomUUID();
            String extension = FilenameUtils.getExtension(upload.getOriginalFilename());

            byte[] bytes = upload.getBytes();

            // 실제 이미지 저장 경로
            
            
            String path =  req.getSession().getServletContext().getRealPath("/");
            
        	String folderPath = path+ File.separator+"image"; //폴더 경로
        	File Folder = new File(folderPath);

        	// 해당 디렉토리가 없다면 디렉토리를 생성.
        	if (!Folder.exists()) {
        		try{
        		    Folder.mkdir(); //폴더 생성합니다. ("새폴더"만 생성)
        		    System.out.println("폴더가 생성완료.");
        	        } 
        	        catch(Exception e){
        		    e.getStackTrace();
        		}        
                 }else {
        		System.out.println("폴더가 이미 존재합니다..");
        	}
            
            
            String imgUploadPath = path + File.separator+"image" + File.separator+ uuid + "." + extension;

            // 이미지 저장
            out = new FileOutputStream(imgUploadPath);
            out.write(bytes);
            out.flush();

            // ckEditor 로 전송
            printWriter = res.getWriter();
            String callback = req.getParameter("CKEditorFuncNum");
            String fileUrl = "http://tomcat.jikwang.net/ctor/image/" + uuid + "." + extension;

            printWriter.println("<script type='text/javascript'>"
                    + "window.parent.CKEDITOR.tools.callFunction("
                    + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
                    +"</script>");

            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null) { out.close(); }
                if(printWriter != null) { printWriter.close(); }
            } catch(IOException e) { e.printStackTrace(); }
        }
    }
	
	//검색 기능
	@GetMapping("blindsearch")
	public String search(String keyword, Model model) {
		List<BlindDTO> searchList = blindService.searchBlinds(keyword);
		
		model.addAttribute("searchList", searchList);
		
		return "blindsearch";
	}
	
	//검색 후 페이징 처리
	@GetMapping("blindsearch")
	public String search(String keyword, Model model, @PageableDefault(sort = "nickName", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<BlindDTO> searchList = blindService.searchBlindsList(keyword, pageable);
		
		model.addAttribute("searchList", searchList);
		model.addAttribute("keyword", keyword);
		model.addAttribute("previous",pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());
		model.addAttribute("hasNext", searchList.hasNext());
		model.addAttribute("hasPrev", searchList.hasPrevious());
		
		return "blindsearch";
	}
}
	    