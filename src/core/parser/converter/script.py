#!/usr/bin/env python
# -*- coding: utf-8 -*-
import sys
import latex2mathml.converter
latex_input = unicode(sys.argv[1], "utf-8")
mathml_output = latex2mathml.converter.convert(latex_input)
print unicode(mathml_output, "utf-8")
exit()