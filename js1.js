obj = {};

obj.changeStr = function (obj) {
    obj.setStr("my js str");
};

obj.printFirstChar = function (obj) {
    console.log(obj.getStr()[0]);
};

obj.changeStrViaPy = function (py, obj) {
    py.setStr(obj, "my js str 2");
};

obj.addFn = function (obj) {
    obj.show = function (x) {
        console.log(x);
    };
};

obj.addMember = function (obj) {
    obj.k1 = "js k1";
};

obj.callFn = function (pyObj, fn) {
    console.log(fn);
    pyObj.callFn(function (x) {
        fn(x);
    });
};

obj.makePyObj = function () {
    var c1 = Polyglot.eval("python", "C1()");
    obj.addFn(c1);
    c1.showYourself()
};

obj;
