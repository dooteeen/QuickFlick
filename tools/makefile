# simple makefile to run script and move output files

PYTHON = pipenv run python

EXISTS_VENV = [ -d ./venv ]

ARG_DEBUG = true
ARG_RELEASE = false

CP_TARGET = ./../app/src/main/res/
DOCUMENT_CD_TARGET = ./../app/src/main/assets

.PHONY: help
help:
	@echo "Tasks"
	@echo "  - setup   : setup pipenv environment"
	@echo "  - debug   : run all scripts with debug mode."
	@echo "  - release : run all scripts with release mode."
	@echo "  - ***.py  : run selected script with release mode."
	@echo "Requires"
	@echo "  - Pipenv ('pipenv' command)"
	@echo "  - Cairo"

.PHONY: setup
setup:
	export PIPENV_VENV_IN_PROJECT=true
	-$(EXISTS_VENV) && pipenv update || pipenv install

.PHONY: debug
debug: clean
	rm -rf wiki/
	@echo
	@echo --- Dubug mode ON ---
	@echo
	-$(PYTHON) indicator_morse.py $(ARG_DEBUG)
	-$(PYTHON) keyboard_layout.py $(ARG_DEBUG)
	-$(PYTHON) keyicon.py $(ARG_DEBUG)
	-$(PYTHON) document.py $(ARG_DEBUG)

.PHONY: release
release: clean \
 document.py indicator_morse.py keyboard_layout.py keyicon.py

.PHONY: document.py
document.py:
	$(PYTHON) $@ $(ARG_RELEASE)
	cp ./output/document/*.html $(DOCUMENT_CD_TARGET)
	@echo
	@echo Download github-markdown-css.
	-[ -d ./github-markdown-css ] \
		&& git pull \
		|| git clone https://github.com/sindresorhus/github-markdown-css
	cp ./github-markdown-css/github-markdown.css $(DOCUMENT_CD_TARGET)

.PHONY: indicator_morse.py
indicator_morse.py:
	$(PYTHON) $@ $(ARG_RELEASE)
	cp -r ./output/indicator/* $(CP_TARGET)

.PHONY: keyboard_layout.py
keyboard_layout.py:
	$(PYTHON) $@ $(ARG_RELEASE)
	cp -r ./output/keyboard/* $(CP_TARGET)

.PHONY: keyicon.py
keyicon.py:
	$(PYTHON) $@ $(ARG_RELEASE)
	cp -r ./output/icons/* $(CP_TARGET)

%.py:
	@echo You have to make new task if $@ exists.

.PHONY: clean
clean:
	rm -rf output/*
	rm -rf tmp/*

# vim: set noexpandtab
