package com.callor.book.persistance;

import com.callor.book.model.BookVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<BookVO, String> {

}
