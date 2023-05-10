package KHOneTop.Thx2GettinaJob.bookmark.repository;

import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkCount;
import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("SELECT b.examId FROM Bookmark b WHERE b.userId = :userId")
    List<Long> findExamIdByUserId(@Param("userId") Long userId);

    @Query("SELECT new KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkCount(b.examName, COUNT(b)) FROM Bookmark b GROUP BY b.examName ORDER BY COUNT(b) DESC")
    List<BookmarkCount> findTop5PopBookmarkCount();
}
