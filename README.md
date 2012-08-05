nutz
====

`nutz` is yet another Markdown processor for the JVM. It is a handcoded parser that generates an Abstract Syntax Tree before emitting the final HTML code. It makes use of Pepmint to support code highlighting in mentioned language.

Features
--------
* Support for standard Markdown syntax per Daring Fireball suite

Continuous Integration
----------------------
The **library** is continuously integrated and unit tested using the *Travis CI system*.

Current status of branch `MASTER`: [![Build Status](https://secure.travis-ci.org/sangupta/nutz.png?branch=master)](http://travis-ci.org/sangupta/nutz)

The library is tested against

* Oracle JDK 7
* Oracle JDK 6
* Open JDK 7
* Open JDK 6

Versioning
----------

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, 
`nutz` will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the follow format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major
* New additions without breaking backward compatibility bumps the minor
* Bug fixes and misc changes bump the patch

For more information on SemVer, please visit http://semver.org/.

License
-------
	
Copyright (c) 2012, Sandeep Gupta

The project uses various other libraries that are subject to their
own license terms. See the distribution libraries or the project
documentation for more details.

The entire source is licensed under the Apache License, Version 2.0 
(the "License"); you may not use this work except in compliance with
the LICENSE. You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
