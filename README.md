# 깃 사용 방법

## 1. clone 받는다.
```bash
git clone [HTTPS 형식]
# 원격저장소를 로컬저장소에 복제(깃 클론)
```

## 2. 복제한 폴더에 들어가서 잘 받아졌는지 확인

```bash
cd SSAFY_STUDY
# 복제한 로컬저장소로 이동

git log
# 기존에 있던 로그 확인

git branch -a
# 기존에 있던 branch 확인
```

## 3. branch 생성

```bash
git branch [생성할 이름]
```

## 4. branch 이동

```bash
git checkout [이동할 branch 이름]
```

## 5. 작업한거 add, commit, pull, push 하기

```bash
git add .
git commit -m '[message]'
git pull orgin [자신의 branch 이름]
git push  origin [자신의 branch 이름]
```

## 6. pull requests하기
git pull origin main
