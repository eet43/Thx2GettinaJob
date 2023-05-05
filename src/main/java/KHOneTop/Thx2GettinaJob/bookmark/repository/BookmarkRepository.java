package KHOneTop.Thx2GettinaJob.bookmark.repository;

import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkRepositoryCustom {
    @Query("SELECT b.examName FROM Bookmark b WHERE b.userId = :userId")
    List<String> findExamNamesByUserId(@Param("userId") Long userId);
}
