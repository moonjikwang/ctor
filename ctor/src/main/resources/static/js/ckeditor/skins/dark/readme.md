### CKEditor Basic Dark Skin

##### by **Biskrem** - Basend on "Moono"
---

> first of all a screnshot:

![CKEditor Moono Basic Dark Theme by Biskrem Screenshot](https://github.com/BiskremMuhammad/ckeditor-basic-moono-dark-biskrem/blob/master/screenshot.png "CKEditor Moono Basic Dark Theme by Biskrem")

## Setup

* first install CKEditor, as for laravel project [follow these steps](https://stackoverflow.com/a/46838583/4846859).

* after successfully get it to work, add this skin folder to CKEditor `skins` folder, for laravel you will find it in the path:


```
public/vendor/unisharp/laravel-ckeditor/skins
```

* right after pasting this folder into the `skins` Directory, open `config.js` form CKEditor Base Directory - `public/vendor/unisharp/laravel-ckeditor` for laravel - **AND Replace its content with this:**

```php
/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' }
	];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';

	config.skin = 'ckeditor-basic-moono-dark-biskrem';
};
```


> EXPLANATION

```php
// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' }
	];
```

**this to minify the editor to the basic tools only**

and finally this line of code:

```php
config.skin = 'ckeditor-basic-moono-dark-biskrem';
```

**to actually skin the editor to our dark theme** *`ckeditor-basic-moono-dark-biskrem` implies the skin folder name in `skins` folder*

---
---

> Here is the rest of `README.md` of the original "Moono" skin that this one is based on


"Moono" Skin
====================

This skin has been chosen for the **default skin** of CKEditor 4.x, elected from the CKEditor
[skin contest](http://ckeditor.com/blog/new_ckeditor_4_skin) and further shaped by
the CKEditor team. "Moono" is maintained by the core developers.

For more information about skins, please check the [CKEditor Skin SDK](http://docs.cksource.com/CKEditor_4.x/Skin_SDK)
documentation.

Features
-------------------
"Moono" is a monochromatic skin, which offers a modern look coupled with gradients and transparency.
It comes with the following features:

- Chameleon feature with brightness,
- high-contrast compatibility,
- graphics source provided in SVG.

Directory Structure
-------------------

CSS parts:
- **editor.css**: the main CSS file. It's simply loading several other files, for easier maintenance,
- **mainui.css**: the file contains styles of entire editor outline structures,
- **toolbar.css**: the file contains styles of the editor toolbar space (top),
- **richcombo.css**: the file contains styles of the rich combo ui elements on toolbar,
- **panel.css**: the file contains styles of the rich combo drop-down, it's not loaded
until the first panel open up,
- **elementspath.css**: the file contains styles of the editor elements path bar (bottom),
- **menu.css**: the file contains styles of all editor menus including context menu and button drop-down,
it's not loaded until the first menu open up,
- **dialog.css**: the CSS files for the dialog UI, it's not loaded until the first dialog open,
- **reset.css**: the file defines the basis of style resets among all editor UI spaces,
- **preset.css**: the file defines the default styles of some UI elements reflecting the skin preference,
- **editor_XYZ.css** and **dialog_XYZ.css**: browser specific CSS hacks.

Other parts:
- **skin.js**: the only JavaScript part of the skin that registers the skin, its browser specific files and its icons and defines the Chameleon feature,
- **icons/**: contains all skin defined icons,
- **images/**: contains a fill general used images,
- **dev/**: contains SVG source of the skin icons.

License
-------

Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.

Licensed under the terms of any of the following licenses at your choice: [GPL](http://www.gnu.org/licenses/gpl.html), [LGPL](http://www.gnu.org/licenses/lgpl.html) and [MPL](http://www.mozilla.org/MPL/MPL-1.1.html).

See LICENSE.md for more information.
