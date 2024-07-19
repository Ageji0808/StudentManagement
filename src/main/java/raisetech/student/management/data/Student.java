package raisetech.student.management.data;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
@Schema(description = "受講生")
@Getter
@Setter

public class Student {

  @Pattern(regexp = "^\\d+$", message = "数字のみを入力してください。")
  private String id;
  @NotBlank
  private String name;
  @NotBlank
  private String hurigana;
  @NotBlank
  private String nickname;
  @NotBlank
  @Email
  private String mailaddress;
  @NotBlank
  private String area;

  private int age;
  @NotBlank
  private String sex;

  private String remark;

  private boolean isDeleted;



    ;}

