package com.example.musiccollection.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.musiccollection.entity.Genre;
import com.example.musiccollection.entity.Music;
import com.example.musiccollection.form.MusicEditForm;
import com.example.musiccollection.form.MusicRegisterForm;
import com.example.musiccollection.form.UserMusicEditForm;
import com.example.musiccollection.form.UserMusicRegisterForm;
import com.example.musiccollection.repository.GenreRepository;
import com.example.musiccollection.repository.MusicRepository;

@Service
public class MusicService {
     private final MusicRepository musicRepository;
     private final GenreRepository genreRepository;
     
     public MusicService(MusicRepository musicRepository, GenreRepository genreRepository) {
    	 this.musicRepository = musicRepository;
    	 this.genreRepository = genreRepository;
     }
     
     @Transactional
     public void create(MusicRegisterForm musicRegisterForm) {
    	 Music music = new Music();
    	 MultipartFile imageFile = musicRegisterForm.getImageFile();
    	 MultipartFile musicFile = musicRegisterForm.getMusicFile();
    	 
    	 if (!imageFile.isEmpty()) {
    		 String imageName = imageFile.getOriginalFilename();
    		 String hashedImageName = generateNewFileName(imageName);
    		 Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
    		 copyImageFile(imageFile, filePath);
    		 music.setImageName(hashedImageName);
    	 }
    	 
    	 if (!musicFile.isEmpty()) {
    		 String musicFileName = musicFile.getOriginalFilename();
    		 String hashedMusicFileName = generateNewFileName(musicFileName);
    		 Path filePath = Paths.get("src/main/resources/static/music/" + hashedMusicFileName);
    		 copyMusicFile(musicFile, filePath);
    		 music.setMusicFile(hashedMusicFileName);
    	 }
    	 
    	 //ジャンルの処理
    	 String genreName = musicRegisterForm.getGenreName();
    	 Genre genre = genreRepository.findByGenreName(genreName)
    			 .orElseGet(() -> {
    				 Genre newGenre = new Genre();
    				 newGenre.setGenreName(genreName);
    				 return genreRepository.save(newGenre);
    			 });
    	 
    	 music.setSongTitle(musicRegisterForm.getSongTitle());
    	 music.setArtist(musicRegisterForm.getArtist());
    	 music.setDescription(musicRegisterForm.getDescription());
    	 music.setGenre(genre);
    	 music.setPrice(musicRegisterForm.getPrice());
    	 music.setEmail(musicRegisterForm.getEmail());
    	 
    	 musicRepository.save(music);
     }
     
     @Transactional
     public void userCreate(UserMusicRegisterForm userMusicRegisterForm) {
    	 Music music = new Music();
    	 MultipartFile imageFile = userMusicRegisterForm.getImageFile();
    	 MultipartFile musicFile = userMusicRegisterForm.getMusicFile();
    	 
    	 if (!imageFile.isEmpty()) {
    		 String imageName = imageFile.getOriginalFilename();
    		 String hashedImageName = generateNewFileName(imageName);
    		 Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
    		 copyImageFile(imageFile, filePath);
    		 music.setImageName(hashedImageName);
    	 }
    	 
    	 if (!musicFile.isEmpty()) {
    		 String musicFileName = musicFile.getOriginalFilename();
    		 String hashedMusicFileName = generateNewFileName(musicFileName);
    		 Path filePath = Paths.get("src/main/resources/static/music/" + hashedMusicFileName);
    		 copyMusicFile(musicFile, filePath);
    		 music.setMusicFile(hashedMusicFileName);
    	 }
    	 
    	 //ジャンルの処理
    	 String genreName = userMusicRegisterForm.getGenreName();
    	 Genre genre = genreRepository.findByGenreName(genreName)
    			 .orElseGet(() -> {
    				 Genre newGenre = new Genre();
    				 newGenre.setGenreName(genreName);
    				 return genreRepository.save(newGenre);
    			 });
    	 
    	 music.setSongTitle(userMusicRegisterForm.getSongTitle());
    	 music.setArtist(userMusicRegisterForm.getArtist());
    	 music.setDescription(userMusicRegisterForm.getDescription());
    	 music.setGenre(genre);
    	 music.setPrice(userMusicRegisterForm.getPrice());
    	 music.setEmail(userMusicRegisterForm.getEmail());
    	 
    	 musicRepository.save(music);
     }
     
     @Transactional
     public void update(MusicEditForm musicEditForm) {
    	 Music music = musicRepository.getReferenceById(musicEditForm.getMusicId());
    	 MultipartFile imageFile = musicEditForm.getImageFile();
    	 MultipartFile musicFile = musicEditForm.getMusicFile();
    	 
    	 if (!imageFile.isEmpty()) {
    		 String imageName = imageFile.getOriginalFilename();
    		 String hashedImageName = generateNewFileName(imageName);
    		 Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
    		 copyImageFile(imageFile, filePath);
    		 music.setImageName(hashedImageName);
    	 }
    	 
    	 if (!musicFile.isEmpty()) {
    		 String musicFileName = musicFile.getOriginalFilename();
    		 String hashedMusicFileName = generateNewFileName(musicFileName);
    		 Path filePath = Paths.get("src/main/resources/static/music/" + hashedMusicFileName);
    		 copyMusicFile(musicFile, filePath);
    		 music.setMusicFile(hashedMusicFileName);
    	 }
    	 
    	 //ジャンルの処理
    	 String genreName = musicEditForm.getGenreName();
    	 Genre genre = genreRepository.findByGenreName(genreName)
    			 .orElseGet(() -> {
    				 Genre newGenre = new Genre();
    				 newGenre.setGenreName(genreName);
    				 return genreRepository.save(newGenre);
    			 });
    	 
    	 music.setSongTitle(musicEditForm.getSongTitle());
    	 music.setArtist(musicEditForm.getArtist());
    	 music.setDescription(musicEditForm.getDescription());
    	 music.setGenre(genre);
    	 music.setPrice(musicEditForm.getPrice());
    	 music.setEmail(musicEditForm.getEmail());
    	 
    	 musicRepository.save(music);
     }
     
     @Transactional
     public void userUpdate(UserMusicEditForm userMusicEditForm) {
    	 Music music = musicRepository.getReferenceById(userMusicEditForm.getMusicId());
    	 MultipartFile imageFile = userMusicEditForm.getImageFile();
    	 MultipartFile musicFile = userMusicEditForm.getMusicFile();
    	 
    	 if (!imageFile.isEmpty()) {
    		 String imageName = imageFile.getOriginalFilename();
    		 String hashedImageName = generateNewFileName(imageName);
    		 Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
    		 copyImageFile(imageFile, filePath);
    		 music.setImageName(hashedImageName);
    	 }
    	 
    	 if (!musicFile.isEmpty()) {
    		 String musicFileName = musicFile.getOriginalFilename();
    		 String hashedMusicFileName = generateNewFileName(musicFileName);
    		 Path filePath = Paths.get("src/main/resources/static/music/" + hashedMusicFileName);
    		 copyMusicFile(musicFile, filePath);
    		 music.setMusicFile(hashedMusicFileName);
    	 }
    	 
    	 //ジャンルの処理
    	 String genreName = userMusicEditForm.getGenreName();
    	 Genre genre = genreRepository.findByGenreName(genreName)
    			 .orElseGet(() -> {
    				 Genre newGenre = new Genre();
    				 newGenre.setGenreName(genreName);
    				 return genreRepository.save(newGenre);
    			 });
    	 
    	 music.setSongTitle(userMusicEditForm.getSongTitle());
    	 music.setArtist(userMusicEditForm.getArtist());
    	 music.setDescription(userMusicEditForm.getDescription());
    	 music.setGenre(genre);
    	 music.setPrice(userMusicEditForm.getPrice());
    	 music.setEmail(userMusicEditForm.getEmail());
    	 
    	 musicRepository.save(music);
     }
     
     public String generateNewFileName(String originalFileName) {
    	    String uuid = UUID.randomUUID().toString();
    	    int dotIndex = originalFileName.lastIndexOf(".");
    	    String extension = dotIndex == -1 ? "" :originalFileName.substring(dotIndex);
    	    String baseName = dotIndex == -1 ? originalFileName : originalFileName.substring(0, dotIndex);
    	    
    	    System.out.println("generateNewFileName:" + originalFileName.toString());
    	    
    	    return baseName + "_" + uuid + extension;
     } 	 
   
     // 画像ファイルを指定したファイルにコピーする
     public void copyImageFile(MultipartFile imageFile, Path filePath) {           
    	 try {
             Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
             System.out.println("File saved to: " + filePath.toString());
             System.out.println("File exists: " + Files.exists(filePath));
         } catch (IOException e) {
             e.printStackTrace();
         } 
     } 
     
  // 音楽ファイルを指定したファイルにコピーする
     public void copyMusicFile(MultipartFile musicFile, Path filePath) {           
         try {
             Files.copy(musicFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
         } catch (IOException e) {
             e.printStackTrace();
         }          
     } 
}