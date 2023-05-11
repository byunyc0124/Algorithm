import sys
from collections import deque
input=sys.stdin.readline

N, L, R = map(int, input().split())
pp = [list(map(int, input().split())) for _ in range(N)]

dy = [1, 0, -1, 0]
dx = [0, -1, 0, 1]
ans = 0

def bfs(sy, sx):
    global isMove

    chk[sy][sx] = True
    q = deque()
    q.append((sy, sx))
    union = [] # 연합한 나라
    union.append([sy, sx])
    union_pp = pp[sy][sx] # 연합한 나라의 인구수
    cnt = 1 # 연합한 나라의 수
    while q:
        y, x = q.popleft()
        for k in range(4):
            ny = y + dy[k]
            nx = x + dx[k]
            if 0 <= ny < N and 0 <= nx < N and not chk[ny][nx]:
                if L <= abs(pp[ny][nx] - pp[y][x]) <= R:  # 연합할 수 있는 인구 차이라면
                    chk[ny][nx] = True
                    q.append((ny, nx))
                    union.append((ny, nx))
                    union_pp += pp[ny][nx]
                    cnt += 1
                    isMove = True

    num = union_pp // cnt # 연합한 나라의 인구수 변경
    return num, union


while True: # 안움직일 때까지
    chk = [[False] * N for _ in range(N)]
    isMove = False # 한번이라도 움직였다면 일수 + 1 해줘야함
    temp = []
    for i in range(N):
        for j in range(N):
            if not chk[i][j]:
                if not chk[i][j]:
                    num, union = bfs(i, j)
                    temp.append((num, union))

    for (t, u) in temp:
        for y, x in u:
            pp[y][x] = t

    if not isMove:
        break
    ans += 1

print(ans)