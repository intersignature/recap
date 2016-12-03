""" BaseConverter """
def base_con(inp, inp2, ans, chk):
    """ main """
    lis = ["A", "B", "C", "D", "E", "F"]
    if inp == 0 and chk == None:
        return 0
    if inp != 0:
        if inp % inp2 >= 10:
            ans += lis[inp % inp2 % 10]
            return base_con(inp // inp2, inp2, ans, "")
        else:
            ans += str(inp % inp2)
            return base_con(inp // inp2, inp2, ans, "")
    else: return "".join(reversed(list(ans)))

print(base_con(int(input()), int(input()), "", None))
