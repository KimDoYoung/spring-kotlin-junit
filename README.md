# spring-kotlin-junit

## 개요

* 인터넷 강의를 보고 따라해 봄
* springboot를 써서 RestApi를 개발하는데, kotlin언어를 사용하고 junit을 TDD방식으로 개발
* mockk를 사용
* spring+java조합을 가늠해 봄.

## kotlin언어에 대한 정리를 함.

- JetBrain사 에서 만든 언어 (intelliJ IDEA 개발사)
- 자바와 호환, JVM에서 수행됨
- C#, Javascript같은 언어에 있는 특징을 java와 결합한 느낌

### 언어적 요점

    * val과 var가 있다.

### 클래스
```kotlin
class Person(private val name: String, private var age: Int, smile: Boolean){
    private val nickName: String
    init{
        val smiley = if(smile) ":)" else ":("
        nickName = "$name $smiley"
    }
    fun description(): String{
        return "Name: $name, age: $age"
    }
    fun hadBirthday(){
        age += 1
    }
}
```

### null safety

```kotlin
fun main(args: Array<String>) {
    //nullable -> name이 널인지 아닌지 물어봐야한다. name은 널가능인가?
    val name: String? = "Honggildong"
    println("length : ${name?.length}")

    //Safe Call with let -> msg가 널인지 아닌지 물어봐서 let을 하자, msg가 널이 아니면
    val msg: String? = "Hello"
    msg?.let {
        println("message is $msg")
    }
    //Elvis-operatolr ?:
    val len = if (name != null)
                name.length
             else
                -1 
    //name이 널이면 -1
    
    //확실히 널이 아니면
    val len2 = name?.length ?: -1;
    println("the length ${name!!.length}")
}
```

### Lambda

```kotlin

    val customers = listOf<Customer>(
        Customer("Hong", 32),
        Customer("Kim", 43),
        Customer("Lee", 29),
    )
    val names = customers.filter{it.age>30}.map{it.name}
    for (name in names){
        println(name)
    }
    val myPredicate = {num: Int -> num > 10}
    //all, any
    val numbers = listOf<Int>(3, 4, 5, 21, 34, 9)
    val check1 = numbers.all( myPredicate )
    println(check1)

    val check2 = numbers.any { it > 10 }
    println(check2)

    //count, find
    val count = numbers.count{ it > 10}
    println(count)

    val num = numbers.find { it > 10 }
    println(num)

    val num1 = numbers.find { it > 100 }
    println(num1)
```