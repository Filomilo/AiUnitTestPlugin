from unittest import TestCase

from Calculator import Calculator


class TestCalculator(TestCase):
    def test_add(self):
        assert Calculator.add(1, 2) == 3
    def test_subtract(self):
        assert Calculator.subtract(1, 2) == -1
    def test_multiply(self):
        assert (Calculator.multiply(2, 3)) == 6
    def test_divide(self):
        assert (Calculator.divide(5, 2) == 2.5)