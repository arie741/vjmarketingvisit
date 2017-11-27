var vpic = 1;
var vcp = 1;
var mk = 1;

function addpic(){
	$('#picadd').append('<input  type="text" name="pic'+ (vpic + 1) +'" placeholder="Person In Charge ' + (vpic + 1) + '"><input  type="text" name="jabatan' + (vpic + 1) + '" placeholder="Jabatan"><input  type="text" name="telppic' + (vpic + 1) + '" placeholder="Telp"><br>');
	vpic = vpic + 1;
}

function addcp(){
	$('#cpadd').append('<input  type="text" name="cp' + (vcp + 1) + '" placeholder="Contact Person ' + (vcp + 1) + '"> <input  type="text" name="jabatancp' + (vcp + 1) + '" placeholder="Jabatan"><input  type="text" name="telpcp' + (vcp + 1) + '" placeholder="Telp"><br>');
	vcp = vcp + 1;
}

function addmk(){
	$('#mkadd').append('<input  type="text" name="mkosis' + (mk + 1) + '" placeholder="Mantan Ketua Osis ' + (mk + 1) + '"> <input  type="text" name="jabatanmk' + (mk + 1) + '" placeholder="Jabatan"><input  type="text" name="telpmk' + (mk + 1) + '" placeholder="Telp"> <br>');
	mk = mk + 1;
}