class C1:
    def showStr1(self, obj):
       print(obj.getStr())

    def showStr2(self, obj):
        print(obj.str)

    def changeStr2(self, obj):
        obj.str = "my py str"

    def setStr(self, obj, v):
        obj.str = v;

    def printFirstChar(self, obj):
        print(obj.str[0])

    def showYourself(self):
        self.show(self)

    def addMember(self):
        self.k2 = 'py k2'

    def callFn(self, fn):
        print(fn)
        fn({'x': 'string from python'})

    def applyFn(self, fn):
        a = 1;
        a = fn(a);
        print('after fn: ' + str(a));

C1()
