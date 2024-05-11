# Festival Guide
무박 2일 해커톤 토이 프로젝트입니다. 

[공공 데이터 API](https://www.data.go.kr/data/15101578/openapi.do#/) 로 부터 축제 정보를 얻어올 수 있는 앱입니다.

https://github.com/jhj0517/Festival-Guide/assets/97279763/b868dca7-6a5f-46b8-a248-991ec2185dcc

# Feature
- 축제 위치, 일정 등의 정보를 RecyclerView 로 제공 (시간 순 정렬)
- 축제 및 행사 키워드 검색
- 선호하는 축제를 RecyclerView 에서 좋아요를 클릭해 `Room` 으로 로컬 DB에 저장
    - 저장된 축제는 개인 페이지에서 관리 가능

# Overview
| Technology | Usage |
| ---------- | ----- |
| Retrofit | 공공 데이터 REST API 요청 |
| Gson | 축제 데이터 응답 데이터 클래스 Json 직렬화 |
| Room | 축제 "좋아요" 기능을 로컬 DB로 구현. |
| Glide | 축제 이미지 표시 |

# Part
- `Recyclerview` 에서 `Filterable` 을 활용한 축제 검색 구현
    - [한글 초성 검색 라이브러리](https://github.com/jhj0517/KoreanChoseongSearch) 활용
- REST API 의 엔드포인트 별 `Retrofit` 인터페이스 작성

# Learned
- **다른 안드로이드 앱 개발자와 협업**해볼 수 있었음.
- **단기간에 많은 기능**을 구현해야 했기 때문에, **효율적인 협업 및 의사소통** 능력을 기를 수 있었음.
  
