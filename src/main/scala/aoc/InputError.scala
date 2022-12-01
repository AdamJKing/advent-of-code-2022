package aoc

import scala.util.control.NoStackTrace

final class InputError(msg: String) extends Throwable(msg) with NoStackTrace
