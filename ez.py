""" """
def eiei():
    """ """
    lis = []
    ans = 0
    for i in range(int(input())):
        lis.append(list(map(int, input().split())))
    print(lis)
    maxx_index = lis[0].index(max(lis[0]))
    for i in lis:
        temp = []
         
        for j in range(min(2, max(1, len(i)))):
            if i == lis[0]:
                temp.append(i[maxx_index])
            else:
                temp.append(i[maxx_index+j])
        maxx_index = i.index(max(temp))
        ans += max(temp)
        print(temp, maxx_index, i)
    print(ans)
         
eiei()
