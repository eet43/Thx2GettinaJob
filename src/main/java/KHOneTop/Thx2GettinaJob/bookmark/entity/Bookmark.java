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
    private String examName;


    public static Bookmark create(Long userId, String examName) {
        return Bookmark.builder()
                .userId(userId)
                .examName(examName)
                .build();
    }

}
