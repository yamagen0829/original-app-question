package com.example.musiccollection.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.musiccollection.entity.Music;

public interface MusicRepository extends JpaRepository<Music, Integer>{
	@Query("SELECT m FROM Music m WHERE "
		     + "(LOWER(m.songTitle) LIKE %:songTitle% OR :songTitle IS NULL OR :songTitle = '') AND "
		     + "(LOWER(m.artist) LIKE %:artist% OR :artist IS NULL OR :artist = '')")
      public Page<Music> findBySongTitleAndArtistLike(@Param("songTitle") String songTitle, @Param("artist") String artist, Pageable pageable);
}