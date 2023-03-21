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

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.ctor.dto.BlindDTO;
import com.ctor.dto.BlindPageRequestDTO;
import com.ctor.service.BlindService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class BlindController {

	@Autowired
	BlindService blindService;
	
	@GetMapping("blind")
	public void blind(BlindPageRequestDTO pageRequestDTO,Model model) {
		model.addAttribute("pageResObj",blindService.getList(pageRequestDTO));
	}
	
	@GetMapping("blindWrite")
	public void blindWrite() {
	}
	@GetMapping({"blindRead","blindmodify"})
	public void read(long bno, @ModelAttribute("requestDTO") BlindPageRequestDTO pageRequestDTO, Model model) {
		BlindDTO dto = blindService.findById(bno);
		model.addAttribute("dto",dto);
		System.out.println("불러온 dto" + dto);
		model.addAttribute("requestDTO",pageRequestDTO);
		model.addAttribute("pageResObj",blindService.getList(pageRequestDTO));
	}
	
	@PostMapping("blindWrite")
	public String postImage(BlindDTO dto,RedirectAttributes redirectAttributes) {
		System.out.println("=========입력받은값 :"+dto);
		Long bno = blindService.register(dto);
		redirectAttributes.addAttribute("bno",bno);
		return "redirect:blindRead";
	}


	    @PostMapping("/fileUpload")
	    @ResponseBody
	    public String handleImageUpload(@RequestParam("upload") MultipartFile file,HttpServletResponse res,HttpServletRequest req) {
	    	UUID uuid = UUID.randomUUID();
	    	PrintWriter printWriter = null;
	    	OutputStream out = null;
	    	byte[] bytes;
			try {
				bytes = file.getBytes();
	    	String fileName = file.getOriginalFilename();
	    	String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
	    	 String imgUploadPath = "img"+ File.separator+"upload" + File.separator + uuid + "." + extension;
	    	 out = new FileOutputStream(imgUploadPath);
	            out.write(bytes);
	            out.flush();
	            
	            printWriter = res.getWriter();
	            String callback = req.getParameter("CKEditorFuncNum");
	            String fileUrl = "img/upload/" + uuid + "." + extension;

	            printWriter.println("<script type='text/javascript'>"
	                    + "window.parent.CKEDITOR.tools.callFunction("
	                    + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
	                    +"</script>");

	            printWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
	    }

	}
	    