/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.Contestant;

import com.lyns.Category.Category;
import com.lyns.Category.CategoryRepository;
import com.lyns.Category.CategoryService;
import com.lyns.Event.Event;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.Random;
/**
 *
 * @author HP
 */
@Service
public class ContestantServiceImpm implements ContestantService{
private @Autowired ContestantRepository CR;
private @Autowired CategoryService CatS;
   private static final Random RANDOM = new Random(System.currentTimeMillis());
 
@Override
    public Contestant saveContestant(Contestant c) {
    try{
    return    CR.save(c);
  //  return "CONTESTANT HAS BEEN SAVED SUCCESSFULLY";
    }catch (Exception ex){
    
  return null;
    }
    }

    @Override
    public String updateContestant(Contestant c) {
     try{
       CR.save(c);
    return "CONTESTANT HAS BEEN SAVED SUCCESSFULLY";
   }catch (Exception ex){
      return "FATAL ERROR PLEASE CONTACT ADMIN "+ex.toString();
   
     
     }
        
        
    }
     @Override
    public String updateContestant(Contestant c,MultipartFile file) {
     try{
         Runnable r=()->{
                   String fileName=org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());

             try {
                 uploadPicture(file, c, fileName);
             } catch (IOException ex) {
                 Logger.getLogger(ContestantServiceImpm.class.getName()).log(Level.SEVERE, null, ex);
             }
     c.setContestantPicture("/stud-pics/"+LocalDate.now().getYear()+"/"+c.getId()+"/"+fileName);
 
       CR.save(c);
     };
      r.run();
    
    return "CONTESTANT HAS BEEN SAVED SUCCESSFULLY";
   }catch (Exception ex){
      return "FATAL ERROR PLEASE CONTACT ADMIN "+ex.toString();
   
     
     }
        
        
    }

    @Override
    public List<com.lyns.Contestant.Contestant> Contestant() {
       return CR.findAll();
    }

    @Override
    public Optional<com.lyns.Contestant.Contestant> findContestantById(com.lyns.Contestant.Contestant contestant) {
    
    return CR.findById(contestant.getId());
    
    }

    @Override
    public List<com.lyns.Contestant.Contestant> getContestantBasedOnEvent(Event event) {
      return event.getContestant();
    }
   public String uploadPicture(MultipartFile multiPartFile,Contestant contestant,String fileName) throws IOException{
        BufferedImage im=ImageIO.read(multiPartFile.getInputStream());
       ByteArrayOutputStream OS=new ByteArrayOutputStream();
    ImageIO.write(im,StringUtils.getFilenameExtension(multiPartFile.getOriginalFilename()) ,OS);
    byte[] b=OS.toByteArray();
    
        try{
     String uploadDir="stud-pics/"+LocalDate.now().getYear()+"/"+contestant.getId();
        Path
                uploadPath=Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
        Files.createDirectories(uploadPath);
        }
        InputStream inputStream=new ByteArrayInputStream(b);
        
        Path filePath=uploadPath.resolve(fileName);
        Files.copy( inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
        return "successfully uploaded";
    }catch(IOException ex){
    return "error"+ex.toString();
    }
    
    }

    @Override
    public Optional<com.lyns.Contestant.Contestant> findContestantByCode(com.lyns.Contestant.Contestant contestant) {
    return Contestant().stream().filter(c->c.getContestantIdNumber().equals(contestant.getContestantIdNumber())).findFirst();
    }

    @Override
    public Map<Category, List<com.lyns.Contestant.Contestant>> ContestantsCat(Event event) {
   Map<Category, List<com.lyns.Contestant.Contestant>>CatContestant=new HashMap<>();
   List<Category>categories=
                 event.getCategories().stream().
                  sorted(Comparator.comparing(Category::getCategoryName)).collect(Collectors.toList());
   
   categories.forEach(c->{
   CatContestant.put(c,c.getContestant() );
   
   
   });
   
   return CatContestant; }
    

    @Override
    public List<com.lyns.Contestant.Contestant> ContestnatByCategory(Category category) {
    
        return CatS.findCategoryById(category.getId()).get().getContestant();
    }

    @Override
    public List<Object> graphData(Category category) {
        List<Object>data=new ArrayList<>();
        CatS.findCategoryById(category.getId()).get().getContestant().stream().forEach(c->{
        String text=""+c.getContestantIdNumber()+"";
            data.add("{ y: "+c.getTotalVotes()+", label: "+c.getId()+" }");
        
        });
        
    return data;
    }
    
    @Override
  public Map<Category,List<Object>>graphDatas(Event event){
	  
      Map<Category,List<Object>>graphdataTemp=new HashMap<>();
	    event.getCategories().stream().forEach(c->{
	    		
	    	graphdataTemp.put(c,graphData(c));	
	    });
	  
	  
	 return graphdataTemp; 
  }
   
   /*
    * dataPoints: [{ x: 10, y: 71 },{ x: 20, y: 55 },{ x: 30, y: 50 },
			{ x: 40, y: 65 },{ x: 50, y: 92, indexLabel: "Highest" },
			{ x: 60, y: 68 },{ x: 70, y: 38 },{ x: 80, y: 71 },{ x: 90, y: 54 },
			{ x: 100, y: 60 },{ x: 110, y: 36 },{ x: 120, y: 49 },{ x: 130, y: 21, indexLabel: "Lowest" }
		]
     */

    @Override
    public Optional<com.lyns.Contestant.Contestant> findContestantByPhoneNumber
        (com.lyns.Contestant.Contestant contestant) {
   
        return Contestant().stream().filter(c->c.getContestantPhoneNumber()
                .equals(contestant.getContestantPhoneNumber())).findFirst();
        }
        
}
