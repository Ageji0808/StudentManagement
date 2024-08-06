package raisetech.student.management.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentsCourses {

  private String courseID;
  private String studentID;
  @Size(max = 50, message = "コース名は最大50文字です")
  private String courseName;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  @Schema(description = "申し込み状況")
  private String status;

  @Schema(description = "本申し込みフラグ")
  private boolean fullApplicationFlag;

  // ステータスを更新するメソッド

  public void updateStatus() {
    if (status == null) {
      status = "仮申し込み";
    }



  }
}


