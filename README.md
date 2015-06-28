## LBTC : LEVELS BEYOND TEMPLATE CREATOR
### brought to you by *f-it-up-friday*

-----

A CLI template generator for Angular and React.

Think:

```
$ rails new controller 
or 
$ yo new react-fire-app 

now you can :

$ lbtc -c MainController,scope,q,backend -d myDirective,something, else -f davidController -m tester

which gives you a controller like this:
---------------------------------------
angular.module('tester').controller('MainController', ['scope', 'q', 'backend', function (scope, q, backend) {}]);

**

and a directive like this:
-------------------------
angular.module('tester').directive('myDirective', ['something', 'something', 'else', function (something, something, else) {
       return {
        restrict: 'E',
        controller: 'MainController',
        scope: true,
        link: function (scope, element, attributes, controllers) {}
};
}]);
```
In files: 
*MainController-controller.js*,

*MainController-directive.js*.

Run **--help** for all options and flags.

---

We are looking for feedback on our cli naming conventions.

All feedback welcome through issues.

Let us know how you would like to be able to use it.

Thanks!

@jnels124 @dviramontes


## Usage

For angular usage :

- set enviorment variable : `$ export LBTC_FRAMEWORK=angular`

For react usage :

- set enviorment variable : `$ export LBTC_FRAMEWORK=react`

The framework will default to **angular** if none is set.
## Framework Assumptions

- **Angular v1.3**

- **React v 1.3**
    - **ES6 via babel**
	- **JSX**
	- **commonjs**


## Building EXE
- add lein-bin to your lein profile 
```:plugins [[lein-bin "0.3.4"]]```
- `$ lein bin`  -> ./target/lbtc
- `$ ./target/lbtc -h`

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
