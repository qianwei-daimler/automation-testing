# demo-spock

### 介绍

test 目录下为使用spock的单元测试代码，以及spring环境下使用spock的代码


### 详细使用说明
[jvm123.com - Java技术分享站](http://jvm123.com/?s=spock)

### 使用方法

#### 1. 基本使用

测试类继承 `spock.lang.Specification`

```groovy
package com.yawn.spock

import CalculateService
import spock.lang.Shared
import spock.lang.Specification

/**
 * spock 测试
 * @author yawn*     2019/6/10 9:43
 */
class CalculateSpec extends Specification {

    @Shared
    CalculateService calculateService

    // 初始化
    def setupSpec() {
        calculateService = new CalculateService()
        println ">>>>>>   setupSpec"
    }

    def setup() {
        println ">>>>>>   setup"
    }

    def cleanup() {
        println ">>>>>>   cleanup"
    }

    def cleanupSpec() {
        println ">>>>>>   cleanupSpec"
    }

    // 声明一个测试
    def "test plus 1"() {
        given: "准备数据"
        def a = 1
        def b = 1 << 1

        when: "测试方法"
        def c = calculateService.plus(a, b)

        then: "校验结果"
        c == (1 << 2) - 1

        println ">>>>>>   test plus 1"
    }

    def "test person use with(p)"() {
        given: "init a person"
        Date now = new Date()
        Person p = new Person(name: "yawn", age: 18, birthday: now)

        expect: "测试p"
        with(p) {
            name == "yawn"
            age < 20
            birthday == now
        }
    }

    def "多个预期值的测试使用 verifyAll()"() {
        when:
        Person p = new Person(name: "yawn", age: 18)

        then:
        verifyAll(p) {
            name == "yawn"
            age < 20
        }
    }

    def "没有参数的 verifyAll"() {
        when:
        int z1 = calculateService.plus(1, 1)
        int z2 = calculateService.plus(1, 2)
        int z3 = calculateService.plus(1, 3)

        then:
        verifyAll {
            z1 == 1
            z2 == 3
            z3 == 3
        }
    }

    // 使用where
    def "computing the maximum of two numbers"() {
        expect:
        Math.max(a, b) == c

        where:
        a << [5, 3] // 执行两次测试，值依次为5，3，下面类似
        b << [1, 9] // << is a Groovy shorthand for List.add()
        c << [5, 9]
    }

    def "test1"() {
        given: "准备mock数据"

        expect: "测试方法"
        z == calculateService.plus(x, y)

        where: "校验结果"
        x | y || z
        1 | 0 || 1
        2 | 1 || 3

    }

    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
//        name     | length
//        "Spock"  | 5
//        "Kirk"   | 4
//        "Scotty" | 6

        // where 的两种方式任选一种
        name << ["Yawn", "Tim"]
        length << [4, 3]
    }

    def "多次执行测试用例"() {
        def a = 0

        expect: "aaa"
        3 * calculateService.plusPlus(a) == 3

        // 执行3次后结果为 3
    }

    static class Person {
        private String name
        private int age
        private Date birthday
    }
}
```
#### 使用桩函数

```groovy
package com.yawn.spock

import CalculateService
import spock.lang.Specification


/**
 * <pre>
 *
 * Stub与Mock
 * （1）相同点
 *      Stub和Mock对象都是用来模拟外部依赖，使我们能控制。
 *      如果被测程序、系统或对象，我们称之为A。在测试A的过程中，
 *      A需要与程序、系统或对象B进行交互，那么Stub/Mock就是用来模拟B的行为来与A进行交互。
 * （2）不同点
 *      Stub，也即“桩”，很早就有这个说法了，主要出现在集成测试的过程中，
 *      从上往下的集成时，作为下方程序的替代。作用如其名，就是在需要时，
 *      能够发现它存在，即可。就好像点名，“到”即可。
 *      Mock，主要是指某个程序的傀儡，也即一个虚假的程序，
 *      可以按照测试者的意愿做出响应，返回被测对象需要得到的信息。
 *      也即是要风得风、要雨得雨、要返回什么值就返回什么值。
 *
 *      总体来说，stub完全是模拟一个外部依赖，用来提供测试时所需要的测试数据。
 *      而mock对象用来判断测试是否能通过，也就是用来验证测试中依赖对象间的交互能否达到预期。
 * </pre>
 * @author yawn*     2019/6/10 14:52
 */
class MockSpec extends Specification {

    def "mock 测试桩"() {
        given: "构造测试桩"
        CalculateService calculateService = Stub(CalculateService)
        calculateService.plusPlus(_) >> 1

        when:
        int x = calculateService.plusPlus(12)
        int y = calculateService.plusPlus(3)

        then:
        x == 1
        y == 1
    }

    def "mock 测试桩得到不同的多个值"() {
        given: "构造测试桩"
        CalculateService calculateService = Stub(CalculateService)
        calculateService.plusPlus(_) >>> [1, 2, 3]

        when:
        int x = calculateService.plusPlus(12)
        int y = calculateService.plusPlus(3)
        int z = calculateService.plusPlus(2)

        then:
        x == 1
        y == 2
        z == 3
    }
}
```
#### 3. spring环境中使用

只需要加入注解 `org.springframework.boot.test.context.SpringBootTest` 即可在测试的时候启动spring容器

```groovy
package com.yawn.spock

import CalculateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification


/**
 *
 * @author yawn*     2019/6/10 16:14
 */
//@WebAppConfiguration
//@ContextConfiguration(classes = [BookPubApplication.class,
//        TestMockBeansConfig.class],loader = SpringApplicationContextLoader.class)
@SpringBootTest
class SpringBootSpec extends Specification {

    @Autowired
    CalculateService calculateService;

    def "spring boot test"() {
        expect: "asas"
        z == calculateService.minus(x, y)

        where:
        x << [9, 8, 7]
        y << [6, 5, 4]
        z << [3, 3, 3]
    }

    def "spring boot test2"() {
        expect: "asas"
        z == calculateService.minus(x, y)

        where:
        x | y | z
        9 | 8 | 1
        6 | 5 | 1
        3 | 3 | 0
    }
}
```  

### 详细使用说明
[jvm123.com - Java技术分享站](http://jvm123.com/2019/08/spock/)

### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
