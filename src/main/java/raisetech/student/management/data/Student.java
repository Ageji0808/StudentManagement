package raisetech.student.management.data;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
@Schema(description = "受講生")
@Getter
@Setter

public class Student {

  @Pattern(regexp = "^\\d+$", message = "数字のみを入力してください。")
  private String id;
  @NotBlank(message = "名前は必須です")
  @Size(max = 50, message = "名前は最大50文字です")
  private String name;
  @NotBlank(message = "ふりがなは必須です")
  @Size(max = 50, message = "ふりがなは最大50文字です")
  private String hurigana;
  @NotBlank(message = "ニックネームは必須です")
  @Size(max = 50, message = "ニックネームは最大50文字です")
  private String nickname;
  @NotBlank(message = "メールアドレスは必須です")
  @Email(message = "有効なメールアドレスを入力してください")
  private String mailaddress;
  @Size(max = 100, message = "地域は最大100文字です")
  private String area;

  @Min(value = 0, message = "年齢は0以上でなければなりません")
  @Max(value = 150, message = "年齢は150以下でなければなりません")
  private int age;

  private String sex;

  @Size(max = 255, message = "備考は最大255文字です")
  private String remark;

  private boolean isDeleted;



    ;}

