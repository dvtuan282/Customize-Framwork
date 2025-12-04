
## Giải thích cấu trúc dự án (package, class)
 - 

## Các hàm common có sẵn

- package 'bussiness' nơi chứa các class def bussiness
- package 'commons' nơi chứa các class action common cho cả project như: click, enterTheValue, getText,.... Và class stepActions and defActions
- package "actions" chứa các action class dùng chung
- package "resource/pages" nơi chứa xpath với các file định dạng .yaml
- package "configs" nơi chứa các class cấu hình
- package "runner" nơi chứa class runner
- class "StepActions" định nghĩa tất cả các action thành các steps phục vụ log từng action lên report.
- class "StepDef" class map các step java sang thành các step cucumber viết trong file feature
- class "YamlLocatorReader" đọc xpath từ file yaml
- class "LocatorResolver" các định giá trị xpath thông qua tên file, field trong file ví dụ (@loginPage.btnLogin - đọc xpath từ file loginPage và field btnLogin)
- class "ParameterType" customized lại param cucumber thay string bằng str


## Running Tests

- clean report and run all feature:

```bash
  mvn clean verify
```

- run all feature:

```bash
  mvn verify
```

- run feature with tag and envName

```bash
   mvn verify -Denvironment="ios" -Dtags="@tagName"
```

- update report

```bash
   mvn serenity:aggregate
```


