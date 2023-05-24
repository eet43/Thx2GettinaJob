package KHOneTop.Thx2GettinaJob.bookmark.entity;

import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookmark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long examId;

    @Column(nullable = false)
    private Boolean isPublic;


    public static Bookmark create(Long userId, Long examId, Boolean isPublic) {
        return Bookmark.builder()
                .userId(userId)
                .examId(examId)
                .isPublic(isPublic)
                .build();
    }

}
