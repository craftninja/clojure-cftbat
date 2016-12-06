# README

Steps taken to "get here"

### Chapter One - Building, Running, and the REPL
1. Create a new Clojure app using Leiningen
  1. `$ lein new app clojure-noob`
  1. open `src/clojure-noob/core.clj` and edit the print statement
  1. compile and run in terminal with `$ lein run`
1. Create and run a standalone java file to distribute and for others to run (within the ignored directory target)
  * `$ lein uberjar` - creates two new files
    * `clojure-noob-0.1.0-SNAPSHOT-standalone.jar`
    * `clojure-noob-0.1.0-SNAPSHOT.jar`
  * `$ java -jar target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar`
1. Use and play with the lein REPL
  * `$ lein repl`
  * Note that within the REPL, you are in the `clojure-noob.core` namespace. The only function defined is `-main`, and we can execute that function with `=> (-main)`
  * Also within the REPL, we can execute some basic Clojure functions:
    * `=> (+ 1 2 3 4)`
    * `=> (* 1 2 3 4)`
    * `=> (first [1 2 3 4])`
  * exit the REPL with `=> exit`

### Chapter Two - something something emacs something
1. Open Atom
1. Install Parinfer

### Chapter Three - Do Things: A Clojure Crash Course

#### Operators
1. general form: `(operator operand1 operand2 ... operandn)`
1. no need for commas between operands (aka arguments), commas are "turned into" whitespace
1. `if` operator

  ```clojure
  (if true
    "return true"
    "return false")
  ; => "return true"

  (if false
    "return something amazing")
  ; => nil

  (if true
    (do (println "It's TRUE!")
        "Super true.")
    (do (println "NOT TRUE.")
        "Super not true."))
  ; => It's TRUE!
  ; => "Super true."
  ```
1. `when` operator

  ```clojure
  (when true
    (println "do all these things!")
    "really, all of them.")
  ; => do all these things!
  ; => "really, all of them."
  ```

1. `nil? nil` => `true`
1. `nil` is falsy, `false` is `false`, everything else is `true` / truthy
1. `(= 1 1)` => `true`
1. `or` operator returns the first truthy value or last value

  ```clojure
  (or false nil :large_I_mean_venti :why_cant_I_just_say_large)
  ; => :large_I_mean_venti
  (or false nil)
  ; => nil
  ```

1. `and` operator returns the last truthy value or first falsy

  ```clojure
  (and :free_wifi :hot_coffee)
  ; => :hot_coffee
  (and :battleship nil false)
  ; => nil
  ```

1. `def` binds names to values
  * bind a name to a value (like assign value to a variable, but there are no variables in Clojure)

    ```clojure
    (def awesome-peeps
      ["Elowyn Platzer Bartel" "Emily Platzer" "Luke Bartel"])
    ```

  * building up strings is different since there are no variables. No mutation!

    Ruby is cool with mutation:

    ```rb
    severity = :mild
    error_message = "OH GOD! IT'S A DISASTER! WE'RE "
    if severity == :mild
      error_message = error_message + "MILDLY INCONVENIENCED!"
    else
      error_message = error_message + "DOOOOOOOMED!"
    end
    ```

    In Clojure, we do it without mutation:

    ```clojure
    (defn error-message
      [severity]
      (str "OH GOD! IT'S A DISASTER! WE'RE "
           (if (= severity :mild)
             "MILDLY INCONVENIENCED!"
             "DOOOOOOOMED!")))

    (error-message :mild)
    ; => "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"
    ```

#### Data structures

1. Numbers - integers, floats, and ratios are handled well
1. Strings
  * double quotes
  * concat via function `str` that returns new string
1. Maps (aka hashes / objects in other languages)
  * can be hash maps OR sorted maps
  * keys are keywords `:first-name`
  * values are any type... strings, numbers, maps, vectors (aka arrays), or functions
  * defined by using hash literal or function:
    * `{:a 1 :b 2} ; literal`
    * `(hash-map :a 1 :b 2) ; function`
  * lookup via `get` function, returns `nil` if not found unless a default value is given. Also lookup via map as "function", keyword as argument. nested maps use `get-in` to go more than one level deep. keywords can also be used as functions to access values in an argument map.

    ```clojure
    (get {:a 1 :b 2} :b)
    ; => 2
    (get {:a 1 :b 2} :c)
    ; => nil
    (get {:a 1 :b 2} :c "unicorns")
    ; => "unicorns"
    ({:a 1 :b 2} :a)
    ; => 1
    ({:a 1 :b 2} :c "unicorns")
    ; => "unicorns"
    (get-in {:a 1 :b {:c 2}} [:b :c])
    ; => 2
    (:a {:a 1 :b 2})
    ; => 1
    ```

1. Vectors (aka arrays in other languages) - 0 indexed collections
  * elements can be of any type, or mixes of types in one vector
  * defined using literal or function
    * `[1,2,3]`
    * `(vector 1 2 3)`
  * accessed by get

    ```clojure
    (get [1 2 3] 0)
    ; => 1
    ```

  * conjoined new elements with function `conj`, elements are added to the end

    ```clojure
    (conj [1 2 3] 4)
    ; => [1 2 3 4]
    ```

1. Lists are similar to vectors. Can not use function `get` on lists. Use when want to add elements to the beginning of a list OR when you are writing a macro (?)
  * defined using literal or `list` function
    * `'(1 2 3 4)`
    * `(list 1 2 3 4)`
  * access elements with `nth` function (significantly slower than vectors and `get` function)

    ```clojure
    (nth '(1 2 3 4) 0)
    ; => 1
    ```

  * conjoined new elements with function `conj`, elements are added to the beginning

    ```clojure
    (conj '(1 2 3) 0)
    ; => (0 1 2 3)
    ```

1. Sets - hashed and sorted. Hashed is more often used. duplicates are eliminated on creation or on `conj` function. can be created from lists or vectors with `set` function. Check for membership with `contains?` function (returns boolean), the keyword you are looking for (returns keyword or nil), or `get` (returns keyword or nil).
  * literal or `hash-set` function
    * `#{"kurt vonnegut" 20 :icicle} ; => #{20 :icicle "kurt vonnegut"}`
    * `(hash-set (hash-set 1 1 2 2)) ; => #{1 2}`
  * `(conj #{:a :b} :b) ; => #{:a :b}`
  * `(set [3 3 3 4 4]) ; => #{3 4}`
  * `(contains? #{:a :b} :a) ; => true`
  * `(:a #{:a :b}) ; => :a`
  * `(get #{:a :b} :a) ; => :a`

#### Functions - calling, diff between funct and macros/special forms, defining, anonymous, and returning functions

Calling
* reminder: Clojure operations all have the same syntax: opening parens, operator, operand(s), closing parens.
* A function call is an operation where the operator is a function or function expression
* A function expression is a function that returns a function
  * simple function expression `(or + -)`
  * function expressions can be used as the operator to another function `((or + -) 1 2 3)`
* functions can also be operands (arguments passed in an operation) `(map inc [0 1 2 3])`

Function Calls, Macro Calls, and Special Forms
* function calls have a function expression as the operator
* macros - later.
  * cannot be used as arguments to functions
* special forms
  * don't always evaluate all of their operands
  * some we have seen so far:
    * `def`
    * `if`
  * cannot be used as arguments to functions

Defining functions
  * five main parts:
    * `defn`
    * Function name
    * A docstring describing the function (optional)
    * Parameters listed in brackets
    * Function body
  * example:

    ```clojure
    (defn too-enthusiastic
      "Return a cheer that might be a bit too enthusiastic"
      [name]
      (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
        "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

    (too-enthusiastic "Zelda")
    ; => "OH. MY. GOD! Zelda YOU ARE MOST DEFINITELY LIKE THE BEST MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"
    ```
  * docstring can be accessed with function `doc` and operand of function name: `(doc map)`
  * number of args must match number of params, but arity overloading can be defined...

    ```clojure
    (defn x-chop
      "Describe the kind of chop you're inflicting on someone"
      ([name chop-type]
         (str "I " chop-type " chop " name "! Take that!")) ; note that each arity definition is enclosed with parens
      ([name]
         (x-chop name "karate")))

    (x-chop "Kanye West" "slap")
    ; => "I slap chop Kanye West! Take that!"
    (x-chop "Kanye East")
    ; => "I karate chop Kanye East! Take that!"
    ```

  * for a variable number of params, use the `&` followed by the name of the list in which the "rest" will be stored

    ```clojure
    (defn team-list
      "Name team name and all the members of the team"
      [team-name & members]
      (str "Members of team " team-name ": "
        (clojure.string/join ", " members)))

    ```

    ```clojure
    (defn codger-communication
      [whippersnapper]
      (str "Get off my lawn, " whippersnapper "!!!"))

    (defn codger
      [& whippersnappers]
      (map codger-communication whippersnappers))

      (codger "Billy" "Anne-Marie" "The Incredible Bulk")
      ; => ("Get off my lawn, Billy!!!" "Get off my lawn, Anne-Marie!!!" "Get off my lawn, The Incredible Bulk!!!")
    ```

  * destructuring a collection passed in as param

    * lists or vectors:

      ```clojure
      (defn my-first
        [[first-thing]] ; Notice that first-thing is within a vector
        first-thing)

      (my-first ["oven" "bike" "war-axe"])
      ; => "oven"
      ```

    * maps:





---

Autogenerated README from `lein new app clojure-noob` below

---

# clojure-noob

FIXME: description

## Installation

Download from http://example.com/FIXME.

## Usage

FIXME: explanation

    $ java -jar clojure-noob-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
