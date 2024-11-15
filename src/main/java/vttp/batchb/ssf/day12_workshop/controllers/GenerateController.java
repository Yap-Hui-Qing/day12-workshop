package vttp.batchb.ssf.day12_workshop.controllers;

import java.io.File;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.batchb.ssf.day12_workshop.models.Image;

@Controller
@RequestMapping("/generate")
public class GenerateController {
    
    // GET /generate
    @GetMapping
    public String generateNumCount(
        @RequestParam(required = false, defaultValue = "fred") String name,
        @RequestParam(required = false, defaultValue = "2") int count,
        @RequestParam(required = false, defaultValue = "5,4,7,3") List<Integer> list,
        Model model) {
            
            List<Integer> index = getIndex(count);
            List<Image> imagesCount = generateImages(index);
            List<Image> imagesList = generateImages(list);
            int imagesListCount = list.size();

            model.addAttribute("name", name.toUpperCase());
            model.addAttribute("imagesCount", imagesCount);
            model.addAttribute("count", count);
            model.addAttribute("list", list);
            model.addAttribute("imagesList", imagesList);
            model.addAttribute("imagesListCount", imagesListCount);

            return "generate";
    }

    public List<Integer> getIndex(int count){
        List<Integer> indexList = new LinkedList<>();

        Random rand = new SecureRandom();

        for (int i = 0; i < count; i++){
            int idx = rand.nextInt(0,31);
            if (!indexList.contains(idx)){
                indexList.add(idx);
            }
        }
        
        return indexList;
    }

    // randomly generate 'count' number of images
    public static List<Image> generateImages(List<Integer> index){
        
        // String path = "src/main/resources/static/numbers";
        // File directory = new File(path);
        // File[] images = directory.listFiles();

        // for (File f : images){
        //     System.out.println(f.getName());
        // }

        String imageName = "";
        List<Image> imageList = new LinkedList<>();

        for (int i = 0; i < index.size(); i++){

            imageName = "/numbers/number%d.jpg".formatted(index.get(i));
            // System.out.println(imageName);
            Image img = new Image(imageName);

            imageList.add(img);
        }

        return imageList;
    }

    
}
